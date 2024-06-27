package com.example.data.repository

import android.util.Log
import com.example.data.model.auth.mapToDomainModel
import com.example.data.datasource.remote.AuthRemoteDataSource
import com.example.domain.model.auth.AccountResponseResult
import com.example.domain.model.auth.LoginResponseResult
import com.example.domain.repository.AccountRepository
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    private val authDataSource: AuthRemoteDataSource,
) : AccountRepository {
    override suspend fun checkApi() {
        val result = authDataSource.checkApi()
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
        return authDataSource.registerUser(
//            name = name,
//            surname = surname,
            login = login,
            password = password,
            code = code
        ).mapToDomainModel()
    }

    override suspend fun signIn(login: String, password: String): AccountResponseResult {
        return authDataSource.signIn(login, password).mapToDomainModel()
    }

    override suspend fun checkSignInData(): AccountResponseResult {
        return authDataSource.checkSignInData().mapToDomainModel()
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
        return authDataSource.getLogin().mapToDomainModel()
    }

    override suspend fun logOut() {
        authDataSource.logOut()
    }

}