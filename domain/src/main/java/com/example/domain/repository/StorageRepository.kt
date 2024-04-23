package com.example.domain.repository

import com.example.domain.model.storage.CreateStorageResult
import com.example.domain.model.storage.StorageModel
import com.example.domain.model.storage.StoragePreviewModelResult
import java.io.File

interface StorageRepository {
    suspend fun getAllStoragesPreview(): StoragePreviewModelResult
    suspend fun getStorageById(id: String): StorageModel
    suspend fun deleteStorage(id: String)
    suspend fun createStorage(title: String, list: List<File>): CreateStorageResult
    suspend fun renameStorage(id: String, name: String)
}