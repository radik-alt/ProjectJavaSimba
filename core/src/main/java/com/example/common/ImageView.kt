package com.example.common

import android.graphics.drawable.Animatable
import android.graphics.drawable.Animatable2
import android.graphics.drawable.AnimatedVectorDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.widget.ImageView
import androidx.annotation.RequiresApi

fun ImageView.startDrawableAnimation() {
    (drawable as? Animatable)?.also {
        if (!it.isRunning)
            it.start()
    }
}

fun ImageView.stopDrawableAnimation() {
    (drawable as? Animatable)?.also {
        if (it.isRunning) {
            it.stop()
        }
    }
}

fun ImageView.stopLoopDrawableAnimation() {
    stopDrawableAnimation()
}