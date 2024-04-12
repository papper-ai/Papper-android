package com.example.papper.features.storage.create_storage.presentation

import java.io.File

data class CreateStorageState(
    val title: String = "",
    val listOfFiles: List<File> = emptyList(),
)

sealed class CreateStorageScreenState {
    object TypingTitle : CreateStorageScreenState()
    object AttachFiles : CreateStorageScreenState()
}