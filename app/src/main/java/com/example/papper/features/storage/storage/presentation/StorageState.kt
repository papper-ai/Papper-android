package com.example.papper.features.storage.storage.presentation

import com.example.papper.features.storage.storage.model.FilePresentationModel

data class StorageState(
    val title: String = "",
    val setOfStorages: Set<FilePresentationModel> = emptySet()
)