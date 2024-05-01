package com.example.data.repository

import android.util.Log
import com.example.data.model.auth.mapToDomainModel
import com.example.data.service.AuthService
import com.example.domain.model.auth.AccountResponseResult
import com.example.domain.model.auth.LoginResponseResult
import com.example.domain.repository.AccountRepository
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    private val service: AuthService,
) : AccountRepository {
    override suspend fun checkApi() {
        val result = service.checkApi()
        if (result.baseResponse.isSuccess) {
            Log.d("Test", "checkApi: Запрос удачный. result: ${result}. Код: ${result.baseResponse.code}")
        }
        else {
            Log.d("Test", "checkApi: Запрос свалился с ошибкой. Код: ${result.baseResponse.code}, сообщение: ${result.baseResponse.msg}")
        }
    }

    override suspend fun signUp(
//        name: String,
//        surname: String,
        login: String,
        password: String,
        code: String
    ): AccountResponseResult {
        return service.registerUser(
//            name = name,
//            surname = surname,
            login = login,
            password = password,
            code = code
        ).mapToDomainModel()
    }

    override suspend fun signIn(login: String, password: String): AccountResponseResult {
        return service.signIn(login, password).mapToDomainModel()
    }

    override suspend fun checkSignInData(): AccountResponseResult {
        return service.checkSignInData().mapToDomainModel()
    }

    override suspend fun changePassword(): AccountResponseResult {
        TODO("Not yet implemented")
    }

    override suspend fun changeName(name: String): AccountResponseResult {
        TODO("Not yet implemented")
    }

    override suspend fun changeSurname(surname: String): AccountResponseResult {
        TODO("Not yet implemented")
    }

    override suspend fun resetPassword(): AccountResponseResult {
        TODO("Not yet implemented")
    }

    override suspend fun getLogin(): LoginResponseResult {
        return service.getLogin().mapToDomainModel()
    }

    override suspend fun logOut() {
        service.logOut()
    }

}