package com.example.domain.usecases.account

import com.example.domain.model.auth.AccountResponseResult
import com.example.domain.repository.AccountRepository
import javax.inject.Inject

class CheckAccountDataUseCase @Inject constructor(
    private val repository: AccountRepository,
) {

    suspend fun execute(): AccountResponseResult {
        return repository.checkSignInData()
    }
}