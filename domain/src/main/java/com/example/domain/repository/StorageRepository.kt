package com.example.domain.repository

import com.example.domain.model.StorageModel
import com.example.domain.model.StoragePreviewModel

interface StorageRepository {
    suspend fun getAllStoragesPreview(): List<StoragePreviewModel>
    suspend fun getStorageById(id: String): StorageModel
    suspend fun deleteStorageById(id: String)
    suspend fun createStorage()
}