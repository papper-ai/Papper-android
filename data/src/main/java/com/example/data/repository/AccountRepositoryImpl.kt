package com.example.data.repository

import com.example.data.service.AuthService
import com.example.domain.repository.AccountRepository
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    service: AuthService,
) : AccountRepository {

    override suspend fun signUp(
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

    override suspend fun changePassword() {
        TODO("Not yet implemented")
    }

    override suspend fun changeName(name: String) {
        TODO("Not yet implemented")
    }

    override suspend fun changeSurname(surname: String) {
        TODO("Not yet implemented")
    }

    override suspend fun resetPassword() {
        TODO("Not yet implemented")
    }
}