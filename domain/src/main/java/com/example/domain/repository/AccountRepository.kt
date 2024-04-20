package com.example.domain.repository

interface AccountRepository {
    suspend fun signUp(name: String, surname: String, login: String, password: String, code: String)
    suspend fun signIn(login: String, password: String)
    suspend fun changePassword()
    suspend fun changeName(name: String)
    suspend fun changeSurname(surname: String)
    suspend fun resetPassword()
}