package com.example.domain.repository

import com.example.domain.model.auth.AccountResponseResult

interface AccountRepository {
    suspend fun signUp(
//        name: String,
//        surname: String,
        login: String,
        password: String,
        code: String
    ): AccountResponseResult
    suspend fun signIn(login: String, password: String): AccountResponseResult
    suspend fun checkSignInData(): AccountResponseResult
    suspend fun changePassword(): AccountResponseResult
    suspend fun changeName(name: String): AccountResponseResult
    suspend fun changeSurname(surname: String): AccountResponseResult
    suspend fun resetPassword(): AccountResponseResult
}