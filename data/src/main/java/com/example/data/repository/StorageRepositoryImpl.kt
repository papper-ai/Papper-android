package com.example.data.repository

import com.example.data.datasource.remote.StorageRemoteDataSource
import com.example.data.model.storage.mapToDomainModel
import com.example.domain.model.storage.CreateStorageResult
import com.example.domain.model.storage.StorageModel
import com.example.domain.model.storage.StoragePreviewModelResult
import com.example.domain.repository.StorageRepository
import java.io.File
import javax.inject.Inject

class StorageRepositoryImpl @Inject constructor(
    private val storageDataSource: StorageRemoteDataSource
) : StorageRepository {
    override suspend fun getAllStoragesPreview(): StoragePreviewModelResult {
        return storageDataSource.getAllStoragesPreview().mapToDomainModel()
    }

    override suspend fun getStorageById(id: String): StorageModel {
        return storageDataSource.getStorageById(id = id).mapToDomainModel()
    }

    override suspend fun deleteStorage(id: String) {
        TODO("Not yet implemented")
    }

    override suspend fun createStorage(title: String, list: List<File>): CreateStorageResult {
        return storageDataSource.createStorage(title = title, list = list).mapToDomainModel()
    }

    override suspend fun renameStorage(id: String, name: String) {
        TODO("Not yet implemented")
    }
}