package com.example.data.repository

import com.example.data.service.AuthService
import com.example.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    service: AuthService
) : AuthRepository {
    override suspend fun registerUser(
        name: String,
        surname: String,
        login: String,
        password: String,
        code: String
    ) {
        TODO("Not yet implemented")
    }

    override suspend fun signIn(login: String, password: String) {
        TODO("Not yet implemented")
    }

    override suspend fun restoreCode() {
        TODO("Not yet implemented")
    }
}