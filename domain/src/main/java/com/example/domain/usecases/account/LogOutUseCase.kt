package com.example.domain.usecases.account

import com.example.domain.repository.AccountRepository
import javax.inject.Inject

class LogOutUseCase @Inject constructor(
    private val repository: AccountRepository
) {
    suspend fun execute() = repository.logOut()
}