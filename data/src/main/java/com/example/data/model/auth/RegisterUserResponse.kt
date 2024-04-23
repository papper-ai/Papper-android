package com.example.data.model.auth

import com.example.data.base.BaseResponse
import com.example.domain.model.auth.AccountResponseResult

data class RegisterUserResponse(
    val baseResponse: BaseResponse,
    val successToken: String,
    val refreshToken: String,
)

internal fun RegisterUserResponse.mapToDomainModel() = AccountResponseResult(
    isSuccess = baseResponse.isSuccess,
    code = baseResponse.code,
    msg = baseResponse.msg
)