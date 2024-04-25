package com.example.data.model.storage

import com.example.data.base.BaseResponse
import com.example.domain.model.storage.DeleteFileResult

data class DeleteFileResponse(
    val baseResponse: BaseResponse,
)

internal fun DeleteFileResponse.mapToDomainModel(): DeleteFileResult =
    DeleteFileResult(
        isSuccess = baseResponse.isSuccess,
        code = baseResponse.code,
        msg = baseResponse.msg,
    )