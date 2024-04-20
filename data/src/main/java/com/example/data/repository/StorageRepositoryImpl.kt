package com.example.data.repository

import com.example.data.datasource.StorageRemoteDataSource
import com.example.data.model.mapToDomainModel
import com.example.domain.model.StorageModel
import com.example.domain.model.StoragePreviewModel
import com.example.domain.repository.StorageRepository
import javax.inject.Inject

class StorageRepositoryImpl @Inject constructor(
    private val storageDataSource: StorageRemoteDataSource
) : StorageRepository {
    override suspend fun getAllStoragesPreview(): List<StoragePreviewModel> {
        return storageDataSource.getAllStoragesPreview().mapToDomainModel()
    }

    override suspend fun getStorageById(id: String): StorageModel {
        return storageDataSource.getStorageById(id = id).mapToDomainModel()
    }

    override suspend fun deleteStorageById(id: String) {
        TODO("Not yet implemented")
    }

    override suspend fun createStorage() {
        TODO("Not yet implemented")
    }
}