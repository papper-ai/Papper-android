package com.example.domain.usecases.account

import com.example.domain.repository.AccountRepository
import javax.inject.Inject

class ChangeNameUseCase @Inject constructor(
    private val repository: AccountRepository,
) {
    suspend fun execute(name: String) {
        repository.changeName(name = name)
    }
}