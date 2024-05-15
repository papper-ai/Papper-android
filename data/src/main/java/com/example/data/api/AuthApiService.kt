package com.example.data.api

import com.example.data.model.auth.CheckApiResponse
import com.example.data.model.auth.GetUserLoginResponse
import com.example.data.model.auth.RefreshTokenRequest
import com.example.data.model.auth.SignInResponse
import com.example.data.utils.Constants
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthApiService {

    @Headers("Content-Type: application/json")
    @GET(Constants.HELLO)
    suspend fun testApi(): Response<CheckApiResponse>


    //@Headers("Content-Type: application/x-www-form-urlencoded")
    @FormUrlEncoded
    @POST(Constants.REGISTER_USER)
    suspend fun registerUser(
        @Field("secret") secret: String,
        @Field("login") login: String,
        @Field("password") password: String
    ): Response<Any>

    @FormUrlEncoded
    @POST(Constants.AUTHORIZE)
    suspend fun signIn(
        @Field("login") login: String,
        @Field("password") password: String
    ): Response<SignInResponse>

    @Headers("Content-Type: application/json")
    @GET(Constants.GET_LOGIN)
    suspend fun fetchUserLogin(): Response<GetUserLoginResponse>


    @POST(Constants.REFRESH_ACCESS_TOKEN)
    suspend fun restoreCode(
        @Body body: RefreshTokenRequest,
    ): Response<SignInResponse>

}