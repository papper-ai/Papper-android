package com.example.data.base

data class BaseResponse (
    val isSuccess: Boolean,
    val code: String,
    val msg: String,
)

internal fun BaseResponse.mapToDomainModel() = com.example.domain.model.BaseResponse(
    isSuccess = isSuccess,
    msg = msg,
)