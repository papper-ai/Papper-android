package com.example.papper.features.storage.create_file.presentation

import com.example.papper.features.storage.create_file.model.AttachPhotoModel
import com.example.papper.features.storage.create_file.model.ConvertedPhotoModel

data class CreateFileState (
    val title: String = "",
    val listOfPhotos: List<AttachPhotoModel> = emptyList<AttachPhotoModel>(),
    val listOfConvertedPhoto: List<ConvertedPhotoModel> = emptyList<ConvertedPhotoModel>(),
)

sealed class CreateFileScreenState {
    object TypingTitle: CreateFileScreenState()
    object AttachPhotos: CreateFileScreenState()
    object Error: CreateFileScreenState()
    object ConfirmCreating: CreateFileScreenState()
}