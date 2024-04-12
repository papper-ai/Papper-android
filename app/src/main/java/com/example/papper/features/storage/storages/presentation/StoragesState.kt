package com.example.papper.features.storage.storages.presentation

import com.example.papper.features.storage.storages.model.StorageDescription

data class StoragesState(
    val listOfStorages: List<StorageDescription> = emptyList()
)

sealed class StoragesScreenState {
    object Loading : StoragesScreenState()
    object Error : StoragesScreenState()
    object Success : StoragesScreenState()
}