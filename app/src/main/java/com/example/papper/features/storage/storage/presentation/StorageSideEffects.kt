package com.example.papper.features.storage.storage.presentation

sealed class StorageSideEffects {
    object ShowLoading: StorageSideEffects()
    object ShowError: StorageSideEffects()
    object ShowSuccess: StorageSideEffects()
}