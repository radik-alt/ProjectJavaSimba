package com.example.common

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

fun <T> Fragment.getNavigationResult(key: String, clearAfterGet: Boolean = false) = try {
    val data =
        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<T>(key)?.value
    if (clearAfterGet) {
        clearCurrentNavigationResult(key)
    }
    data
} catch (_: Throwable) {
    null
}

fun Fragment.clearCurrentNavigationResult(key: String) = try {
    findNavController().currentBackStackEntry?.savedStateHandle?.set(key, null)
} catch (e: Exception) {
    null
}

fun <T> Fragment.setNavigationResult(result: T?, key: String) {
    findNavController().previousBackStackEntry?.savedStateHandle?.set(key, result)
}

/*fun <T> Fragment.setNavigationResult(key: String, result: T?) {
    findNavController().previousBackStackEntry?.savedStateHandle?.set(key, result)
}*/