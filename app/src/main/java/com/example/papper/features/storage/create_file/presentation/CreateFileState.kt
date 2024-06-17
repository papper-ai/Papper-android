package com.example.papper.features.storage.create_file.presentation

import com.example.papper.features.storage.create_file.model.PhotoModel

data class CreateFileState (
    val title: String = "",
    val listOfPhotos: List<PhotoModel> = emptyList<PhotoModel>(),
)

sealed class CreateFileScreenState {
    object TypingTitle: CreateFileScreenState()
    object AttachPhotos: CreateFileScreenState()
    object Error: CreateFileScreenState()
    object ConfirmCreating: CreateFileScreenState()
}