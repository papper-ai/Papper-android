package com.example.papper.features.storage.storage.presentation

sealed class StorageSideEffects {
    object ShowLoading: StorageSideEffects()
    object ShowError: StorageSideEffects()
    object ShowSuccess: StorageSideEffects()
    data class ShowToastAddFileError(val title: String): StorageSideEffects()
    data class ShowToastFileAlreadyExist(val title: String): StorageSideEffects()
    data class ShowToastDeleteFileError(val title: String): StorageSideEffects()
    object NavigateToStoragesScreen: StorageSideEffects()
    object ShowToastDeleteStorageError: StorageSideEffects()
}