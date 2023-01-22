package com.example.projectjavasimba.presentation.profileFragment

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {

    private val uriImage = MutableLiveData<Uri?>(null)

    fun setImage(image:Uri){
        uriImage.value = image
    }

    fun getImage(): MutableLiveData<Uri?> = uriImage
}