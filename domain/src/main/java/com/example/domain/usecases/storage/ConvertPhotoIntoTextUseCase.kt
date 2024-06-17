package com.example.domain.usecases.storage

import com.example.domain.model.storage.ConvertPhotoModel
import com.example.domain.model.storage.ConvertPhotoResult
import com.example.domain.repository.StorageRepository
import javax.inject.Inject

class ConvertPhotoIntoTextUseCase @Inject constructor(
    private val repository: StorageRepository
) {
    suspend fun execute(listOfPhoto: List<ConvertPhotoModel>): ConvertPhotoResult {
        return repository.convertPhoto(list = listOfPhoto)
    }
}