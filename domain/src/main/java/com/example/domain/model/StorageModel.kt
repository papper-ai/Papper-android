package com.example.domain.model

import java.io.File

data class StorageModel(
    val isSuccess: Boolean,
    val code: String,
    val msg: String,
    val id: String,
    val title: String,
    val listOfFiles: List<File>,
)