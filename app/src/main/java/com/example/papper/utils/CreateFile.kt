package com.example.papper.utils

import android.content.Context
import android.os.Environment
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

fun createFile(
    context: Context,
    title: String,
    text: String
): File {
    val directory = File(context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), "MyFolder")
    if (!directory.exists()) {
        directory.mkdirs()
    }

    val file = File(directory, "$title.txt")
    try {
        val fileOutputStream = FileOutputStream(file)
        fileOutputStream.write(text.toByteArray())
        fileOutputStream.flush()
        fileOutputStream.close()
    } catch (e: IOException) {
        e.printStackTrace()
    }

    return file
}