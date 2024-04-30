package com.example.papper.features.storage.create_storage.presentation

import java.io.File

data class CreateStorageState(
    val title: String = "",
    val storageType: String = "",
    val listOfFiles: Set<File> = emptySet(),
)

sealed class CreateStorageScreenState {
    object TypingTitle : CreateStorageScreenState()
    object ChooseStorageType: CreateStorageScreenState()
    object AttachFiles : CreateStorageScreenState()
}

enum class StorageType(val type: String) {
    graph(type = "graph"),
    vector(type = "vector"),
}