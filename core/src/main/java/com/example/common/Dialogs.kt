package com.example.common

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.ContextThemeWrapper
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.viewbinding.ViewBinding
import com.example.core.R

fun dialog(
    activity: ContextThemeWrapper,
    binding: ViewBinding,
    cancelable: Boolean = false,
    fullScreen: Boolean = false
): Dialog {
    (binding.root.parent as? ViewGroup)?.removeAllViews()
    return dialog(activity, binding.root, cancelable, fullScreen)
}

fun dialog(
    activity: ContextThemeWrapper,
    view: View,
    cancelable: Boolean = false,
    fullScreen: Boolean = false
): Dialog {
    return Dialog(activity/*, R.style.DialogStyle*/).apply {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setCancelable(cancelable)
        setContentView(view)
        window?.run {
//            setBackgroundDrawable(ContextCompat.getDrawable(activity, R.drawable.bg_dialog))
            if (fullScreen){
                setLayout(
                    WindowManager.LayoutParams.MATCH_PARENT,
                    WindowManager.LayoutParams.MATCH_PARENT
                )
            }else {
                setLayout(
                    WindowManager.LayoutParams.MATCH_PARENT,
                    WindowManager.LayoutParams.WRAP_CONTENT
                )
            }
//            setWindowAnimations(R.style.DialogAnimations)
        }
    }
}

fun transparentDialog(
    activity: ContextThemeWrapper,
    binding: ViewBinding,
    cancelable: Boolean = false
): AlertDialog {
    return AlertDialog.Builder(activity).apply {
        setView(binding.root)
    }.create().apply {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setCancelable(cancelable)
    }
}