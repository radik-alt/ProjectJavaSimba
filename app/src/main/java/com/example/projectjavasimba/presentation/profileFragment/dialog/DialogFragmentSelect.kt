package com.example.projectjavasimba.presentation.profileFragment.dialog

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.provider.SyncStateContract.Constants
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.projectjavasimba.databinding.DialogLayoutBinding
import com.example.projectjavasimba.presentation.profileFragment.SharedViewModel

class DialogFragmentSelect : DialogFragment() {

    private var _binding:DialogLayoutBinding?=null
    private val binding:DialogLayoutBinding
        get() = _binding ?: throw RuntimeException("DialogLayoutBinding == null")

    private val sharedViewModel:SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.getPhoto.setOnClickListener {

        }

        binding.makePhoto.setOnClickListener {
            if (allPermission()){
                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                getContent.launch(Uri.parse(""))
            } else {
                ActivityCompat.requestPermissions(requireActivity(), PERMISSION, PERMISSION_CODE)
            }
        }

        binding.deletePhoto.setOnClickListener {

        }
    }

    private val getContent = registerForActivityResult(ActivityResultContracts.TakePicture()) { imageUri ->
        if (imageUri != null) {
//            sharedViewModel.setImage(imageUri)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun allPermission() = PERMISSION.all{
        ContextCompat.checkSelfPermission(requireContext(), it) == PackageManager.PERMISSION_GRANTED
    }

    companion object{
        private const val REQUEST_TAKE_PHOTO = 1;
        private const val TAG_IMAGE = "profile_photo"
        private val PERMISSION = arrayOf(Manifest.permission.CAMERA)
        private const val PERMISSION_CODE = 100
    }

}