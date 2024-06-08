package com.example.papper.features.storage.create_file.presentation

import android.graphics.Bitmap

data class CreateFileState (
    val title: String = "",
    val listOfPhotos: List<Bitmap> = emptyList<Bitmap>(),
)

sealed class CreateFileScreenState {
    object TypingTitle: CreateFileScreenState()
    object AttachPhotos: CreateFileScreenState()
    object Error: CreateFileScreenState()
    object ConfirmCreating: CreateFileScreenState()
}