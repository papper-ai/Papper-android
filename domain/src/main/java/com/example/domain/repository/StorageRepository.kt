package com.example.domain.repository

import com.example.domain.model.BaseResponse
import com.example.domain.model.storage.AddFileInStorageResult
import com.example.domain.model.storage.CreateStorageResult
import com.example.domain.model.storage.StorageModel
import com.example.domain.model.storage.StoragePreviewModelResult
import java.io.File

interface StorageRepository {
    suspend fun getAllStoragesPreview(): StoragePreviewModelResult
    suspend fun getStorageById(id: String): StorageModel
    suspend fun deleteStorage(id: String): BaseResponse
    suspend fun createStorage(title: String, type: String, list: List<File>): CreateStorageResult
    suspend fun addFileById(id: String, file: File): AddFileInStorageResult
    suspend fun deleteFileById(vaultId: String, documentId: String): BaseResponse
    suspend fun renameStorage(id: String, title: String): BaseResponse
}