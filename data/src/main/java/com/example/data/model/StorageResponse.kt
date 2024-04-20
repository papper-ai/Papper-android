package com.example.data.model

import java.io.File

data class StorageResponse(
    val id: String,
    val title: String,
    val listOfStorages: List<File>,
)

internal fun StorageResponse.mapToDomainModel() : com.example.domain.model.StorageModel =
    com.example.domain.model.StorageModel(
        id = id,
        title = title,
        listOfFiles = listOfStorages,
    )