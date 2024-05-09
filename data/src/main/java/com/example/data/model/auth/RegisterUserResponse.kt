package com.example.data.model.auth

import com.example.data.base.BaseResponse
import com.example.domain.model.auth.AccountResponseResult
import com.google.gson.annotations.SerializedName

data class RegistrationBody(
    val login: String,
    val password: String,
    @SerializedName("secret")
    val code: String,
)

data class RegisterUserResponse(
    val baseResponse: BaseResponse,
)

internal fun RegisterUserResponse.mapToDomainModel() = AccountResponseResult(
    isSuccess = baseResponse.isSuccess,
    code = baseResponse.code,
    msg = baseResponse.msg
)