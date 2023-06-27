package com.example.projectjavasimba.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VM : BaseViewModel, VB : ViewBinding>() : Fragment() {

    protected abstract val viewModel: VM
    private var bindingRef: VB? = null

    protected val binding: VB
        get() = bindingRef.let {
            if (it != null)
                it
            else {
                val newBd = provideBinding(layoutInflater)
                bindingRef = newBd
                newBd
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.alertText.observe(this) {

        }

        viewModel.toastMessage.observe(this) {

        }
    }

    protected abstract fun provideBinding(inflater: LayoutInflater): VB



}