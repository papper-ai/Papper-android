package com.example.papper.features.storage.create_file.presentation

sealed class CreateFileSideEffects {
    object ShowAttachPhotos: CreateFileSideEffects()
    object ShowConfirmCreating: CreateFileSideEffects()
    data class NavigateToCreateStorageScreen(val title: String, val text: String): CreateFileSideEffects()
    data class NavigateToStorageScreen(val title: String, val text: String): CreateFileSideEffects()
}