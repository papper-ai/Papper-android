package com.example.papper.features.storage.storage.model

import com.example.domain.model.storage.DeleteFileResult

data class PresentationDeleteFileResult(
    val isSuccess: Boolean,
    val code: String,
    val msg: String,
)

internal fun DeleteFileResult.mapToPresentationModel(): PresentationDeleteFileResult =
    PresentationDeleteFileResult(
        isSuccess = isSuccess,
        code = code,
        msg = msg,
    )