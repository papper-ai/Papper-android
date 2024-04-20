package com.example.domain.usecases.account

import com.example.domain.repository.AccountRepository
import javax.inject.Inject

class ChangeSurnameUseCase @Inject constructor(
    private val repository: AccountRepository,
) {
    suspend fun execute(surname: String) {
        repository.changeSurname(surname = surname)
    }
}