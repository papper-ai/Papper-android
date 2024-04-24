package com.example.papper.features.storage.storage.model

import com.example.domain.model.storage.AddFileInStorageResult

data class PresentationAddFileResult(
    val id: String,
    val isSuccess: Boolean,
    val code: String,
    val msg: String,
)

internal fun AddFileInStorageResult.mapToPresentationModel(): PresentationAddFileResult =
    PresentationAddFileResult(
        id = id,
        isSuccess = isSuccess,
        code = code,
        msg = msg,
    )