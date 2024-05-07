package com.example.data.api

import com.example.data.model.auth.CheckApiResponse
import com.example.data.model.auth.GetUserLoginResponse
import com.example.data.model.auth.SignInResponse
import com.example.data.utils.Constants
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthApiService {

    @Headers("Content-Type: application/json")
    @GET(Constants.HELLO)
    suspend fun testApi(): Response<CheckApiResponse>

    suspend fun registerUser(name: String, surname: String, login: String, password: String, code: String)

    @FormUrlEncoded
    @POST("/auth/login")
    suspend fun signIn(
        @Field("login") login: String,
        @Field("password") password: String
    ): Response<SignInResponse>

    @Headers("Content-Type: application/json")
    @GET(Constants.GET_LOGIN)
    suspend fun fetchUserLogin(): Response<GetUserLoginResponse>

    suspend fun restoreCode()

}