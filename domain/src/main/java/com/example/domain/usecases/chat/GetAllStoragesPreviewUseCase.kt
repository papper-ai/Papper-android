package com.example.domain.usecases.chat

import com.example.domain.model.StoragePreviewModel
import com.example.domain.repository.StorageRepository

class GetAllStoragesPreviewUseCase(
    val repository: StorageRepository,
) {
    suspend fun execute(): List<StoragePreviewModel> {
        return repository.getAllStoragesPreview()
    }
}