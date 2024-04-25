package com.example.domain.usecases.storage

import com.example.domain.model.storage.DeleteFileResult
import com.example.domain.repository.StorageRepository
import javax.inject.Inject

class DeleteFileUseCase @Inject constructor(
    private val repository: StorageRepository,
) {
    suspend fun execute(id: String): DeleteFileResult {
        return repository.deleteFileById(id = id)
    }
}