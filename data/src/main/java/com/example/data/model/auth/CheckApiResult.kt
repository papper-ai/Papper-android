package com.example.data.model.auth

import com.example.data.base.BaseResponse

data class CheckApiResponse(
    val message: String,
)

data class CheckApiResult (
    val baseResponse: BaseResponse,
    val result: String,
)