package com.example.papper.features.storage.create_storage.presentation

sealed class CreateStorageSideEffects {
    object ShowAttachFilesScreen: CreateStorageSideEffects()
    object ShowChooseStorageType: CreateStorageSideEffects()
    data class NavigateToStorageScreen(val id: String? = null): CreateStorageSideEffects()
    data class NavigateToCreateChatScreen(val id: String? = null): CreateStorageSideEffects()
    object NavigateToCreateFileScreen: CreateStorageSideEffects()
    object ShowErrorToast: CreateStorageSideEffects()
    object ShowNetworkConnectionError: CreateStorageSideEffects()
}