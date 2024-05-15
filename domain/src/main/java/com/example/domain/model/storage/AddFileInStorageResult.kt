package com.example.domain.model.storage

data class AddFileInStorageResult(
    val id: String,
    val isSuccess: Boolean,
    val code: String,
    val msg: String,
    val text: String,
)