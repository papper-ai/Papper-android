package com.example.papper.features.storage.storage.presentation

import java.io.File

data class StorageState(
    val title: String = "",
    val listOfStorages: List<File> = emptyList()
)