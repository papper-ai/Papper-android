package com.example.domain.usecases.account

import com.example.domain.model.auth.AccountResponseResult
import com.example.domain.repository.AccountRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val accountRepository: AccountRepository,
){
    suspend fun execute(
        name: String,
        surname: String,
        login: String,
        password: String,
        code: String
    ): AccountResponseResult {
        return accountRepository.signUp(
            name = name,
            surname = surname,
            login = login,
            password = password,
            code = code
        )
    }
}