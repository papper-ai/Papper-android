package com.example.domain.model

import java.io.File

data class StorageModel(
    val id: String,
    val title: String,
    val listOfFiles: List<File>,
)