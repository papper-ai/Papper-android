package com.example.domain.repository

import com.example.domain.model.auth.AccountResponseResult
import com.example.domain.model.auth.LoginResponseResult

interface AccountRepository {

    suspend fun checkApi()

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

    suspend fun getLogin(): LoginResponseResult
    suspend fun logOut()
}