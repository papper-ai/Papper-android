package com.example.domain.usecases.storage

import com.example.domain.model.BaseResponse
import com.example.domain.repository.StorageRepository
import javax.inject.Inject

class DeleteFileUseCase @Inject constructor(
    private val repository: StorageRepository,
) {
    suspend fun execute(vaultId: String, documentId: String): BaseResponse {
        return repository.deleteFileById(vaultId = vaultId, documentId = documentId)
    }
}