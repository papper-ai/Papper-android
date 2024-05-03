package com.example.papper.features.storage.storage.model

import com.example.domain.model.BaseResponse

data class PresentationDeleteFileResult(
    val isSuccess: Boolean,
    val msg: String,
)

internal fun BaseResponse.mapToPresentationModel(): PresentationDeleteFileResult =
    PresentationDeleteFileResult(
        isSuccess = isSuccess,
        msg = msg,
    )