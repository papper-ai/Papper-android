package com.example.data.api

import com.example.data.model.auth.CheckApiResponse
import com.example.data.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface AuthApiService {

    @Headers("Content-Type: application/json")
    @GET(Constants.HELLO)
    suspend fun testApi(): Response<CheckApiResponse>

    suspend fun registerUser(name: String, surname: String, login: String, password: String, code: String)

    suspend fun signIn(login: String, password: String)

    suspend fun restoreCode()

}