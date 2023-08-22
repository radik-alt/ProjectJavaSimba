package com.example.common

import java.io.File

fun File.clear() {
    if (this.isDirectory) {
        this.listFiles()?.forEach {
            it.delete()
        }
    }
}