package com.example.domain.usecases

import com.example.domain.repository.AccountRepository
import javax.inject.Inject

class CheckApiUseCase @Inject constructor(
    private val repository: AccountRepository,
) {
    suspend fun execute() {
        repository.checkApi()
    }
}