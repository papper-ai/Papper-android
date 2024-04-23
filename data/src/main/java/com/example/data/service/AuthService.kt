package com.example.data.service

import com.example.data.api.AuthApiService
import com.example.data.base.BaseResponse
import com.example.data.datasource.local.AuthLocalDataSource
import com.example.data.model.auth.RegisterUserResponse
import com.example.data.model.auth.SignInResponse
import com.example.data.utils.BaseResponseImitation
import kotlinx.coroutines.delay
import javax.inject.Inject

class AuthService @Inject constructor(
    private val apiService: AuthApiService,
    private val authLocalDataSource: AuthLocalDataSource,
) {

    suspend fun registerUser(name: String, surname: String, login: String, password: String, code: String): RegisterUserResponse {
        delay(5000)
        val baseResponse = BaseResponseImitation.execute()
        lateinit var resultFromApi: RegisterUserResponse
        if (baseResponse.isSuccess) {
            // TODO убрать комментарии когда будет не заглушка
//            authLocalDataSource.saveLogin(login = login)
//            authLocalDataSource.savePassword(password = password)
            authLocalDataSource.saveSuccessToken(token = "success_tok3n")
            authLocalDataSource.saveRefreshToken(token = "refresh_tok3n")
            resultFromApi = RegisterUserResponse(
                baseResponse = baseResponse,
                successToken = "success_tok3n",
                refreshToken = "refresh_tok3n",
            )
            authLocalDataSource.saveSuccessToken(token = resultFromApi.successToken)
            authLocalDataSource.saveRefreshToken(token = resultFromApi.refreshToken)
            return resultFromApi
        }
        else {
            resultFromApi = RegisterUserResponse(
                baseResponse = baseResponse,
                successToken = "",
                refreshToken = "",
            )
        }
        return resultFromApi
    }

    suspend fun signIn(login: String, password: String): SignInResponse {
        delay(5000)
        val baseResponse = BaseResponseImitation.execute()
        lateinit var resultFromApi: SignInResponse
        if (login == "login" && password == "pass") {
            if (baseResponse.isSuccess) {
                authLocalDataSource.saveLogin(login = login)
                authLocalDataSource.savePassword(password = password)
                authLocalDataSource.saveSuccessToken(token = "success_tok3n")
                authLocalDataSource.saveRefreshToken(token = "refresh_tok3n")
                resultFromApi = SignInResponse(
                    baseResponse = baseResponse,
                    successToken = "success_tok3n",
                    refreshToken = "refresh_tok3n",
                )
            }
            else {
                resultFromApi = SignInResponse(
                    baseResponse = baseResponse,
                    successToken = "",
                    refreshToken = "",
                )
            }
        }
        else {
            resultFromApi = SignInResponse(
                baseResponse = BaseResponse(isSuccess = false, code = "100", msg = "Incorrect login or password"),
                successToken = "",
                refreshToken = "",
            )
        }

        return resultFromApi
    }

    suspend fun checkSignInData(): SignInResponse {
        val baseResponse = BaseResponseImitation.execute()
        lateinit var resultFromApi: SignInResponse
        delay(3000)
        val login = authLocalDataSource.getLogin()
        val password = authLocalDataSource.getPassword()

        if (login != null && password != null) {
            if (login == "login" && password == "pass") {
                if (baseResponse.isSuccess) {
                    resultFromApi = SignInResponse(
                        baseResponse = baseResponse,
                        successToken = "success_tok3n",
                        refreshToken = "refresh_tok3n",
                    )
                }
                else {
                    resultFromApi = SignInResponse(
                        baseResponse = baseResponse,
                        successToken = "",
                        refreshToken = "",
                    )
                }
            }
            else {
                resultFromApi = SignInResponse(
                    baseResponse = BaseResponse(isSuccess = false, code = "100", msg = "Incorrect login or password"),
                    successToken = "",
                    refreshToken = "",
                )
            }
        }
        else {
            resultFromApi = SignInResponse(
                baseResponse = BaseResponse(isSuccess = false, code = "100", msg = "Incorrect login or password"),
                successToken = "",
                refreshToken = "",
            )
        }
        return resultFromApi
    }

    suspend fun restoreCode() {

    }

}