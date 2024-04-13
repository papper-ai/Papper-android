package com.example.papper.features.storage.storage.presentation

sealed class StorageScreenState {
    object  Loading: StorageScreenState()
    object  Success: StorageScreenState()
    object  Error: StorageScreenState()
}