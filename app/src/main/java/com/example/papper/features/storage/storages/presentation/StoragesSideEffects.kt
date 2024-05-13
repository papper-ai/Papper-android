package com.example.papper.features.storage.storages.presentation

sealed class StoragesSideEffects {
    object ShowLoading : StoragesSideEffects()
    object ShowError : StoragesSideEffects()
    object ShowSuccess : StoragesSideEffects()
    data class NavigateToStorageScreen(val id: String) : StoragesSideEffects()
    data class NavigateToCreateChatScreen(val id: String) : StoragesSideEffects()
    object NavigateToCreateStorageScreen : StoragesSideEffects()
    object ShowNetworkConnectionError: StoragesSideEffects()
}