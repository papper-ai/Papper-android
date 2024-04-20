package com.example.domain.usecases.account

import com.example.domain.repository.AccountRepository
import javax.inject.Inject

class ChangePasswordUseCase @Inject constructor(
    private val accountRepository: AccountRepository,
) {
    suspend fun execute(oldPassword: String, newPassword: String) {
        accountRepository.changePassword()
    }
}