package com.example.papper.features.storage.storages.presentation

sealed class StoragesSideEffects {
    object ShowLoading : StoragesSideEffects()
    object ShowError : StoragesSideEffects()
    object ShowSuccess : StoragesSideEffects()
}