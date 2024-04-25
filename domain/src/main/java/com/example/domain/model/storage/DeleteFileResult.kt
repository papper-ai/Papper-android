package com.example.domain.model.storage

data class DeleteFileResult(
    val isSuccess: Boolean,
    val code: String,
    val msg: String,
)