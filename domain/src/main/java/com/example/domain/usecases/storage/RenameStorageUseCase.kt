package com.example.domain.usecases.storage

import com.example.domain.model.BaseResponse
import com.example.domain.repository.StorageRepository
import javax.inject.Inject

class RenameStorageUseCase @Inject constructor(
    private val repository: StorageRepository,
) {
    suspend fun execute(id: String, title: String): BaseResponse {
        return repository.renameStorage(id = id, title = title)
    }
}