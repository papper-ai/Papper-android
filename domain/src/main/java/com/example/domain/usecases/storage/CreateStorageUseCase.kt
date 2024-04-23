package com.example.domain.usecases.storage

import com.example.domain.model.storage.CreateStorageResult
import com.example.domain.repository.StorageRepository
import java.io.File
import javax.inject.Inject

class CreateStorageUseCase @Inject constructor(
    private val repository: StorageRepository,
) {
    suspend fun execute(title: String, list: List<File>) : CreateStorageResult {
        return  repository.createStorage(title = title, list = list)
    }
}