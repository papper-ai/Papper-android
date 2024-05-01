package com.example.data.model.auth

import com.example.data.base.BaseResponse
import com.example.domain.model.auth.LoginResponseResult

data class GetUserLoginResponse (
    val login: String
)

data class GetUserLoginResponseResult (
    val baseResponse: BaseResponse,
    val login: String
)

internal fun GetUserLoginResponseResult.mapToDomainModel(): LoginResponseResult =
        LoginResponseResult(
            isSuccess = baseResponse.isSuccess,
            code = baseResponse.code,
            msg = baseResponse.msg,
            login = login
        )