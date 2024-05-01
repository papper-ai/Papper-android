package com.example.data.model.auth

import com.example.data.base.BaseResponse
import com.example.domain.model.auth.AccountResponseResult
import com.google.gson.annotations.SerializedName

data class SignInResponse (
    @SerializedName("access_token")
    val accessToken: AccessToken,
    @SerializedName("refresh_token")
    val refreshToken: RefreshToken,
)

data class AccessToken(
    val token: String,
)

data class RefreshToken(
    val token: String,
)

data class SignInResponseResult (
    val baseResponse: BaseResponse,
)

internal fun SignInResponseResult.mapToDomainModel(): AccountResponseResult {
    return AccountResponseResult(
        isSuccess = baseResponse.isSuccess,
        code = baseResponse.code,
        msg = baseResponse.msg,
    )
}