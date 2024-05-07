package com.example.data.service

import android.util.Log
import com.example.data.api.AuthApiService
import com.example.data.base.BaseResponse
import com.example.data.datasource.local.AuthLocalDataSource
import com.example.data.model.auth.CheckApiResult
import com.example.data.model.auth.GetUserLoginResponseResult
import com.example.data.model.auth.RegisterUserResponse
import com.example.data.model.auth.SignInResponseResult
import com.example.data.utils.BaseResponseImitation
import kotlinx.coroutines.delay
import javax.inject.Inject

class AuthService @Inject constructor(
    private val apiService: AuthApiService,
    private val authLocalDataSource: AuthLocalDataSource,
) {

    suspend fun checkApi(): CheckApiResult {
        val result = apiService.testApi()

        if (result.isSuccessful) {
            return CheckApiResult(
                baseResponse = BaseResponse(
                    isSuccess = true,
                    code = result.code().toString(),
                    msg = result.message(),
                ),
                result = result.body()?.message ?: ""
            )
        }
        else {
            return CheckApiResult(
                baseResponse = BaseResponse(
                    isSuccess = false,
                    code = result.code().toString(),
                    msg = result.message(),
                ),
                result = ""
            )
        }
    }

    suspend fun registerUser(login: String, password: String, code: String): RegisterUserResponse {
        delay(5000)
        val baseResponse = BaseResponseImitation.execute()
        lateinit var resultFromApi: RegisterUserResponse
        if (baseResponse.isSuccess) {
            // TODO убрать комментарии когда будет не заглушка
//            authLocalDataSource.saveLogin(login = login)
//            authLocalDataSource.savePassword(password = password)
            authLocalDataSource.saveSuccessToken(token = "access_tok3n")
            authLocalDataSource.saveRefreshToken(token = "refresh_tok3n")
            resultFromApi = RegisterUserResponse(
                baseResponse = baseResponse,
                accessToken = "access_tok3n",
                refreshToken = "refresh_tok3n",
            )
            authLocalDataSource.saveSuccessToken(token = resultFromApi.accessToken)
            authLocalDataSource.saveRefreshToken(token = resultFromApi.refreshToken)
            return resultFromApi
        }
        else {
            resultFromApi = RegisterUserResponse(
                baseResponse = baseResponse,
                accessToken = "",
                refreshToken = "",
            )
        }
        return resultFromApi
    }

    suspend fun signIn(login: String, password: String): SignInResponseResult {
        delay(5000)
        lateinit var result: SignInResponseResult

        val resultFromApi = apiService.signIn(login = login, password = password)
        try {
            if (resultFromApi.isSuccessful) {
                authLocalDataSource.saveLogin(login = login)
                authLocalDataSource.savePassword(password = password)
                Log.e("Test", "signIn: ${resultFromApi.body()?.accessToken?.token.orEmpty()}")
                Log.e("Test", "signIn: ${resultFromApi.body()?.refreshToken?.token.orEmpty()}")
                authLocalDataSource.saveSuccessToken(token = resultFromApi.body()?.accessToken?.token.orEmpty())
                authLocalDataSource.saveRefreshToken(token = resultFromApi.body()?.refreshToken?.token.orEmpty())
                result = SignInResponseResult(
                    BaseResponse(
                        isSuccess = true,
                        code = resultFromApi.code().toString(),
                        msg = resultFromApi.message(),
                    )
                )
            } else {
                result = SignInResponseResult(
                    BaseResponse(
                        isSuccess = false,
                        code = resultFromApi.code().toString(),
                        msg = resultFromApi.message(),
                    )
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
            result = SignInResponseResult(
                BaseResponse(
                    isSuccess = false,
                    code = "",
                    msg = "Error: ${e.message}",
                )
            )
        }

        return result
    }

    suspend fun checkSignInData(): SignInResponseResult {
        delay(3000)
        lateinit var result: SignInResponseResult

        val login = authLocalDataSource.getLogin()
        val password = authLocalDataSource.getPassword()

        if (login != null && password != null) {
            val resultFromApi = apiService.signIn(
                login = login,
                password = password
            )
            if (resultFromApi.isSuccessful) {

                Log.e("Test", "signIn: ${resultFromApi.body()?.accessToken?.token.orEmpty()}")
                Log.e("Test", "signIn: ${resultFromApi.body()?.refreshToken?.token.orEmpty()}")
                authLocalDataSource.saveSuccessToken(token = resultFromApi.body()?.accessToken?.token.orEmpty())
                authLocalDataSource.saveRefreshToken(token = resultFromApi.body()?.refreshToken?.token.orEmpty())
                result = SignInResponseResult(
                    BaseResponse(
                        isSuccess = true,
                        code = resultFromApi.code().toString(),
                        msg = resultFromApi.message(),
                    )
                )
            } else {
                result = SignInResponseResult(
                    BaseResponse(
                        isSuccess = false,
                        code = resultFromApi.code().toString(),
                        msg = resultFromApi.message(),
                    )
                )
            }
        } else {
            result = SignInResponseResult(
                BaseResponse(
                    isSuccess = false,
                    code = "",
                    msg = "",
                )
            )
        }

        return result
    }

    suspend fun restoreCode() {

    }

    suspend fun getLogin(): GetUserLoginResponseResult {
        delay(2000)
        lateinit var result: GetUserLoginResponseResult

        val resultFromApi = apiService.fetchUserLogin()
        if (resultFromApi.isSuccessful) {
            result = GetUserLoginResponseResult(
                BaseResponse(
                    isSuccess = true,
                    code = resultFromApi.code().toString(),
                    msg = resultFromApi.message(),
                ),
                login = resultFromApi.body()?.login.orEmpty()
            )
        } else {
            result = GetUserLoginResponseResult(
                BaseResponse(
                    isSuccess = false,
                    code = resultFromApi.code().toString(),
                    msg = resultFromApi.message(),
                ),
                login = resultFromApi.body()?.login.orEmpty()
            )
        }

        return result
    }

    suspend fun logOut() {
        authLocalDataSource.saveLogin(login = "")
        authLocalDataSource.savePassword(password = "")
        authLocalDataSource.saveSuccessToken(token = "")
        authLocalDataSource.saveRefreshToken(token = "")
    }

}