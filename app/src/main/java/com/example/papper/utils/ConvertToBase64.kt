package com.example.papper.utils

import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import android.util.Base64
import androidx.compose.ui.text.LinkAnnotation
import androidx.core.net.toFile
import java.io.File
import java.net.URL
import java.nio.charset.Charset

fun Uri.toBase64(context: Context): String {
    context.contentResolver.openInputStream(this).use { inputStream ->
        return Base64.encodeToString(inputStream?.readBytes(), Base64.DEFAULT)
    }
}