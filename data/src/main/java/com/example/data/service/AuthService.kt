package com.example.data.service

import android.util.Log
import com.example.data.api.AuthApiService
import com.example.data.base.BaseResponse
import com.example.data.datasource.local.AuthLocalDataSource
import com.example.data.model.auth.CheckApiResult
import com.example.data.model.auth.GetUserLoginResponseResult
import com.example.data.model.auth.RefreshTokenRequest
import com.example.data.model.auth.RegisterUserResponse
import com.example.data.model.auth.SignInResponseResult
import kotlinx.coroutines.delay
import okio.IOException
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
        lateinit var result: RegisterUserResponse

        try {
            val resultFromApi = apiService.registerUser(secret = code, login = login, password = password)
            if (resultFromApi.isSuccessful) {
                authLocalDataSource.saveLogin(login = login)
                authLocalDataSource.savePassword(password = password)

                result = RegisterUserResponse(
                    baseResponse = BaseResponse(
                        isSuccess = true,
                        code = resultFromApi.code().toString(),
                        msg = resultFromApi.message(),
                    ),
                )
            } else {
                result = RegisterUserResponse(
                    baseResponse = BaseResponse(
                        isSuccess = false,
                        code = resultFromApi.code().toString(),
                        msg = resultFromApi.message(),
                    )
                )
            }
        } catch (e: IOException) {
            result = RegisterUserResponse(
                baseResponse = BaseResponse(
                    isSuccess = false,
                    code = "0",
                    msg = "Ошибка подключения к интернету"
                )
            )
        } catch (e : Exception) {
            result = RegisterUserResponse(
                baseResponse = BaseResponse(
                    isSuccess = false,
                    code = "0",
                    msg = "Неизвестная ошибка"
                )
            )
        }

        return result
    }

    suspend fun signIn(login: String, password: String): SignInResponseResult {
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

        delay(2000)
        return result
    }

    suspend fun checkSignInData(): SignInResponseResult {
        lateinit var result: SignInResponseResult

        try {
            val login = authLocalDataSource.getLogin()
            val password = authLocalDataSource.getPassword()

            if (login != null && password != null) {
                val resultFromApi = apiService.signIn(
                    login = login,
                    password = password
                )
                if (resultFromApi.isSuccessful) {
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
        } catch (e: IOException) {
            result = SignInResponseResult(
                BaseResponse(
                    isSuccess = false,
                    code = "0",
                    msg = "Ошибка подключения к интернету"
                )
            )
        } catch (e : Exception) {
            result = SignInResponseResult(
                BaseResponse(
                    isSuccess = false,
                    code = "0",
                    msg = "Неизвестная ошибка"
                )
            )
        }

        delay(3000)
        return result
    }

    suspend fun refreshToken(): BaseResponse {
        val resultFromApi = authLocalDataSource.getRefreshToken()?.let {
            apiService.restoreCode(body =  RefreshTokenRequest(refreshToken = it))
        }
        if (resultFromApi?.isSuccessful == true) {
            Log.e("Test", "Попал в refresh")
            Log.e("Test", "accessToken: ${resultFromApi.body()?.accessToken?.token.orEmpty()}")
            Log.e("Test", "refreshToken: ${resultFromApi.body()?.refreshToken?.token.orEmpty()}")
            authLocalDataSource.saveSuccessToken(token = resultFromApi.body()?.accessToken?.token.orEmpty())
            authLocalDataSource.saveRefreshToken(token = resultFromApi.body()?.refreshToken?.token.orEmpty())
            return BaseResponse(
                isSuccess = true,
                code = resultFromApi.code().toString(),
                msg = resultFromApi.message(),
            )
        } else {
            Log.e("Test", "Попал в false")
            return BaseResponse(
                isSuccess = false,
                code = resultFromApi?.code().toString(),
                msg = resultFromApi?.message().orEmpty(),
            )
        }
    }

    suspend fun getLogin(): GetUserLoginResponseResult {
        lateinit var result: GetUserLoginResponseResult

        try {
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
                if (resultFromApi.code() == 401) {
                    refreshToken()
                    getLogin()
                }
                result = GetUserLoginResponseResult(
                    BaseResponse(
                        isSuccess = false,
                        code = resultFromApi.code().toString(),
                        msg = resultFromApi.message(),
                    ),
                    login = resultFromApi.body()?.login.orEmpty()
                )
            }
        } catch (e: IOException) {
            result = GetUserLoginResponseResult(
                BaseResponse(
                    isSuccess = false,
                    code = "0",
                    msg = "Ошибка подключения к интернету"
                ),
                login = ""
            )
        } catch (e : Exception) {
            result = GetUserLoginResponseResult(
                BaseResponse(
                    isSuccess = false,
                    code = "0",
                    msg = "Неизвестная ошибка"
                ),
                login = ""
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