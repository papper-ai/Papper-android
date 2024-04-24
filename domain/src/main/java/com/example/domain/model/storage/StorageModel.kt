package com.example.domain.model.storage

data class StorageModel(
    val isSuccess: Boolean,
    val code: String,
    val msg: String,
    val id: String,
    val title: String,
    val listOfFiles: List<FileDomainModel>,
)

data class FileDomainModel(
    val id: String,
    val title: String,
)