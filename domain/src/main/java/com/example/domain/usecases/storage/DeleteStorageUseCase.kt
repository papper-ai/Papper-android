package com.example.domain.usecases.storage

import com.example.domain.repository.StorageRepository
import javax.inject.Inject

class DeleteStorageUseCase @Inject constructor(
    private val repository: StorageRepository,
){
    suspend fun execute(id: String) {
        repository.deleteStorage(id = id)
    }
}