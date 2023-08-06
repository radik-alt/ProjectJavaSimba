package com.example.profile.presentation.view

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.profile.databinding.DialogLayoutBinding
import com.example.profile.presentation.viewmodel.SharedViewModel
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class DialogFragmentSelect : DialogFragment() {

    private var _binding: DialogLayoutBinding? = null
    private val binding: DialogLayoutBinding
        get() = _binding ?: throw RuntimeException("DialogLayoutBinding == null")

    private var uri: Uri? = null
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = DialogLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.getPhoto.setOnClickListener {
            getContentFromGallery.launch("image/*")
        }

        binding.makePhoto.setOnClickListener {
            if (allPermission()) {
                openCamera()
            } else {
                ActivityCompat.requestPermissions(requireActivity(), PERMISSION, PERMISSION_CODE)
            }
        }

        binding.deletePhoto.setOnClickListener {
            sharedViewModel.setIsDeleteImage(true)
        }
    }

    private val getContentFromGallery = registerForActivityResult(ActivityResultContracts.GetContent()){ imageUri ->
        imageUri?.let { sharedViewModel.setImage(it) }
    }

    private val getContent =
        registerForActivityResult(ActivityResultContracts.TakePicture()) { success ->
            if (success) {
                uri?.let { sharedViewModel.setImage(it) }
            }
        }

    private fun openCamera() {
        val file = createImageFile()
        try {
            uri = FileProvider.getUriForFile(
                requireActivity(),
                "com.example.projectjavasimba.fileProvider",
                file
            )
        } catch (e: Exception) {
            Log.d("OpenCameraContent", e.message.toString())
        }
        if (uri != null) {
            getContent.launch(uri)
        }
    }

    private fun createImageFile(): File {
        val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val imageDirectory = requireActivity().filesDir
        return File.createTempFile(
            "Camera_${timestamp}",
            ".png",
            imageDirectory
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun allPermission() = PERMISSION.all {
        ContextCompat.checkSelfPermission(requireContext(), it) == PackageManager.PERMISSION_GRANTED
    }

    companion object {
        private val PERMISSION =
            arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        private const val PERMISSION_CODE = 100
    }

}