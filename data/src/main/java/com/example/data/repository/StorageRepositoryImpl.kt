package com.example.data.repository

import com.example.data.datasource.remote.StorageRemoteDataSource
import com.example.data.model.storage.mapToDomainModel
import com.example.domain.model.BaseResponse
import com.example.domain.model.storage.AddFileInStorageResult
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

    override suspend fun deleteStorage(id: String): BaseResponse {
        return storageDataSource.deleteStorageById(id = id).mapToDomainModel()
    }

    override suspend fun createStorage(title: String, type: String, list: List<File>): CreateStorageResult {
        return storageDataSource.createStorage(title = title, type = type, listOfFiles = list).mapToDomainModel()
    }

    override suspend fun addFileById(
        id: String,
        file: File
    ): AddFileInStorageResult {
        return storageDataSource.addFileInStorage(id = id, file = file).mapToDomainModel()
    }

    override suspend fun deleteFileById(vaultId: String, documentId: String): BaseResponse {
        return storageDataSource.deleteFileInStorage(vaultId = vaultId, documentId = documentId).mapToDomainModel()
    }

    override suspend fun renameStorage(id: String, name: String) {
        TODO("Not yet implemented")
    }
}