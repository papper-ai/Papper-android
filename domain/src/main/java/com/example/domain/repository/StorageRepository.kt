package com.example.domain.repository

import com.example.domain.model.CreateStorageResult
import com.example.domain.model.StorageModel
import com.example.domain.model.StoragePreviewModelResult
import java.io.File

interface StorageRepository {
    suspend fun getAllStoragesPreview(): StoragePreviewModelResult
    suspend fun getStorageById(id: String): StorageModel
    suspend fun deleteStorage(id: String)
    suspend fun createStorage(title: String, list: List<File>): CreateStorageResult
    suspend fun renameStorage(id: String, name: String)
}