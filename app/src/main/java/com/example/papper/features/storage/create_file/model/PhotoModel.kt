package com.example.papper.features.storage.create_file.model

import android.net.Uri

data class PhotoModel (
    val id: Int,
    var imageUri: Uri,
    var text: String,
)