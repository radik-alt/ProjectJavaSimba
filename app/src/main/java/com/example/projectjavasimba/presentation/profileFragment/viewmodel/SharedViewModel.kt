package com.example.projectjavasimba.presentation.profileFragment.viewmodel

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {

    private val uriImage = MutableLiveData<Uri?>(null)
    private val deleteImage = MutableLiveData<Boolean>()
    fun setImage(image:Uri){
        uriImage.value = image
    }

    fun getImage(): MutableLiveData<Uri?> = uriImage

    fun setIsDeleteImage(delete:Boolean){
        deleteImage.value = delete
    }
    fun getIsDeleteImage() = deleteImage

}