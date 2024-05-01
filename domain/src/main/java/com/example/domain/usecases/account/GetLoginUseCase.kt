package com.example.domain.usecases.account

import com.example.domain.repository.AccountRepository
import javax.inject.Inject

class GetLoginUseCase @Inject constructor(
    private val repository: AccountRepository
) {
    suspend fun execute() = repository.getLogin()
}