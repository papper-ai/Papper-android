package com.example.domain.usecases.storage

import com.example.domain.model.StoragePreviewModel
import com.example.domain.repository.StorageRepository
import javax.inject.Inject

class GetAllStoragesPreviewUseCase @Inject constructor(
    private val repository: StorageRepository,
) {
    suspend fun execute(): List<StoragePreviewModel> {
        return repository.getAllStoragesPreview()
    }
}