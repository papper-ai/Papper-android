package com.example.papper.features.storage.storages.presentation

import com.example.papper.features.storage.storages.model.PresentationStoragePreviewModel

data class StoragesState(
    val listOfStorages: List<PresentationStoragePreviewModel> = emptyList()
)

sealed class StoragesScreenState {
    object Loading : StoragesScreenState()
    object Error : StoragesScreenState()
    object Success : StoragesScreenState()
}