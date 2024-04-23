package com.example.data.api

interface AuthApiService {

    suspend fun registerUser(name: String, surname: String, login: String, password: String, code: String)

    suspend fun signIn(login: String, password: String)

    suspend fun restoreCode()

}