package com.example.papper.utils

import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

object GetFile {
    fun Uri.getFileName (context: Context): String? {
        val cursor = context.contentResolver.query(this, null, null, null, null)
        if (cursor == null || !cursor.moveToFirst()) return null

        val indexName = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
        val fileName = cursor.getString(indexName)
        cursor.close()

        return fileName
    }

    fun Uri.getFile (context: Context): File? {
        val fileDescriptor = context.contentResolver.openFileDescriptor(this, "r", null)
        if (fileDescriptor == null) return null

        val file = File(context.cacheDir, getFileName(context)!!)
        val fileOutputStream = FileOutputStream(file)

        val fileInputStream = FileInputStream(fileDescriptor.fileDescriptor)
        fileInputStream.copyTo(fileOutputStream)
        fileDescriptor.close()

        return file
    }
}