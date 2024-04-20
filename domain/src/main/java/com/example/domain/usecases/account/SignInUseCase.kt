package com.example.domain.usecases.account

import com.example.domain.repository.AccountRepository
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val accountRepository: AccountRepository,
) {
    suspend fun execute(login: String, password: String) {
        accountRepository.signIn(login = login, password = password)
    }
}