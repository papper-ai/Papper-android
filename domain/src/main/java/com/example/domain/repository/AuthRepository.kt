package com.example.domain.repository

interface AuthRepository {
    suspend fun registerUser(name: String, surname: String, login: String, password: String, code: String)
    suspend fun signIn(login: String, password: String)
    suspend fun restoreCode()
}