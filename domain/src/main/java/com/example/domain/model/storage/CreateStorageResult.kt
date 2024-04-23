package com.example.domain.model.storage

data class CreateStorageResult(
    val isSuccess: Boolean,
    val code: String,
    val msg: String,
    val id: String
)