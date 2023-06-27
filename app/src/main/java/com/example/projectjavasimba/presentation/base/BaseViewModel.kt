package com.example.projectjavasimba.presentation.base

import android.app.Application
import android.content.Context
import androidx.annotation.StringRes
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

open class BaseViewModel(application: Application) : AndroidViewModel(application) {

    val toastMessage = MutableLiveData<String>()
    val alertText = MutableLiveData<String>()

    protected val context: Context
        get() = getApplication<Application>().applicationContext

    protected fun getString(@StringRes resId: Int) = context.getString(resId)

    protected fun getString(@StringRes resId: Int, vararg formatArgs: Any?) =
        context.getString(resId, *formatArgs)

}