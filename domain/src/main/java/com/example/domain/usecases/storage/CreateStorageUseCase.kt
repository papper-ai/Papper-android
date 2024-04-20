package com.example.domain.usecases.storage

import com.example.domain.repository.StorageRepository
import javax.inject.Inject

class CreateStorageUseCase @Inject constructor(
    private val repository: StorageRepository,
) {
    suspend fun execute() {
        repository.createStorage()
    }
}