package com.example.common

import android.content.Context
import android.net.Uri
import android.webkit.MimeTypeMap
import androidx.documentfile.provider.DocumentFile

fun Uri.getMimeType(): String {
    val map = MimeTypeMap.getSingleton()
    val ext = MimeTypeMap.getFileExtensionFromUrl(this.toString())
    return map.getMimeTypeFromExtension(ext) ?: ""
}

fun Uri.getMimeType(context: Context): String =
    DocumentFile.fromSingleUri(context, this)?.type ?: this.getMimeType()

fun Uri.getBytesFileSize(context: Context) =
    context.contentResolver.openAssetFileDescriptor(this, "r")?.length ?: 0

fun Uri.getKBytesFileSize(context: Context): Float = getBytesFileSize(context) / 1000f

fun Uri.getMBytesFileSize(context: Context): Float = getBytesFileSize(context) / 1000000f

fun Uri.getGBytesFileSize(context: Context): Float = getBytesFileSize(context) / 1000000000f