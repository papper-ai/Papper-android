package com.example.domain.model.storage

data class StoragePreviewModelResult(
    val isSuccess: Boolean,
    val code: String,
    val msg: String,
    val list: List<StoragePreviewModel>
)

data class StoragePreviewModel(
    val id: String,
    val title: String,
)
