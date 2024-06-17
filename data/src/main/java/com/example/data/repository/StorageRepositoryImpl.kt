package com.example.data.repository

import com.example.data.datasource.remote.StorageRemoteDataSource
import com.example.data.model.storage.mapToDomainModel
import com.example.domain.model.BaseResponse
import com.example.domain.model.storage.AddFileInStorageResult
import com.example.domain.model.storage.ConvertPhotoModel
import com.example.domain.model.storage.ConvertPhotoResult
import com.example.domain.model.storage.CreateStorageResult
import com.example.domain.model.storage.StorageModel
import com.example.domain.model.storage.StoragePreviewModelResult
import com.example.domain.repository.StorageRepository
import java.io.File
import java.util.Base64
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

    override suspend fun renameStorage(id: String, title: String): BaseResponse {
        return storageDataSource.renameStorage(id = id, title = title).mapToDomainModel()
    }

    override suspend fun convertPhoto(list: List<ConvertPhotoModel>): ConvertPhotoResult {
        return storageDataSource.convertPhoto(list).mapToDomainModel()
    }
}