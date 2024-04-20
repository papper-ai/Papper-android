package com.example.domain.usecases.storage

import com.example.domain.model.StorageModel
import com.example.domain.repository.StorageRepository
import javax.inject.Inject

class GetStorageByIdUseCase @Inject constructor(
    private val repository: StorageRepository,
) {
    suspend fun execute(id : String): StorageModel {
        return repository.getStorageById(id = id)
    }
}