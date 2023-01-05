package com.example.projectjavasimba.presentation.profileFragment.dialog

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.example.projectjavasimba.databinding.DialogLayoutBinding

class DialogFragmentSelect : DialogFragment() {

    private var _binding:DialogLayoutBinding?=null
    private val binding:DialogLayoutBinding
        get() = _binding ?: throw RuntimeException("DialogLayoutBinding == null")

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
                startCamera()
            } else {
                ActivityCompat.requestPermissions(requireActivity(), PERMISSION, PERMISSION_CODE)
            }
        }

        binding.deletePhoto.setOnClickListener {

        }
    }


    private fun startCamera() {
        val camera = ProcessCameraProvider.getInstance(requireContext())
        camera.addListener(Runnable {
            val cameraProvider = camera.get()
        }, ContextCompat.getMainExecutor(requireContext()))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun allPermission() = PERMISSION.all{
        ContextCompat.checkSelfPermission(requireContext(), it) == PackageManager.PERMISSION_GRANTED
    }

    companion object{
        private const val TAG = "CAMERAX"
        private const val PERMISSION_CODE = 10
        private val PERMISSION = arrayOf(Manifest.permission.CAMERA)
    }

}