package com.example.domain.usecases.storage

import com.example.domain.model.storage.AddFileInStorageResult
import com.example.domain.repository.StorageRepository
import java.io.File
import javax.inject.Inject

class AddFileInStorageById @Inject constructor(
    private val repository: StorageRepository,
) {
    suspend fun execute(storageId: String, file: File): AddFileInStorageResult {
        return repository.addFileById(id = storageId, file = file)
    }
}