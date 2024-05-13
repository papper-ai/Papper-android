package com.example.papper.features.storage.storage.presentation

sealed class StorageSideEffects {
    object ShowLoading: StorageSideEffects()
    object ShowError: StorageSideEffects()
    object ShowSuccess: StorageSideEffects()
    data class ShowToastAddFileError(val title: String): StorageSideEffects()
    data class ShowToastFileAlreadyExist(val title: String): StorageSideEffects()
    data class ShowToastDeleteFileError(val title: String): StorageSideEffects()
    object ShowRenameErrorToast: StorageSideEffects()
    data class RenameStorage(val id: String, val title: String): StorageSideEffects()
    object NavigateToStoragesScreen: StorageSideEffects()
    data class DeleteStorageAndNavigateToStoragesScreen(val id: String): StorageSideEffects()
    object ShowToastDeleteStorageError: StorageSideEffects()
    object ShowNetworkConnectionError: StorageSideEffects()
}