package com.example.papper.features.storage.create_file.presentation

sealed class CreateFileSideEffects {
    object ShowAttachPhotos: CreateFileSideEffects()
    object ShowConfirmCreating: CreateFileSideEffects()
}