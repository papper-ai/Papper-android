package com.example.papper.features.storage.create_file.presentation

sealed class CreateFileSideEffects {
    object NavigateBack: CreateFileSideEffects()
    data class NavigatePopBack(val title: String, val text: String): CreateFileSideEffects()
}