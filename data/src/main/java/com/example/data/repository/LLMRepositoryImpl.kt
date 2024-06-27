package com.example.data.repository

import com.example.data.datasource.remote.LLMRemoteDataSource
import com.example.data.model.llm.mapToDomainModel
import com.example.domain.model.llm.ConvertedImageResult
import com.example.domain.model.llm.ImageToConvert
import com.example.domain.repository.LLMRepository
import javax.inject.Inject

class LLMRepositoryImpl @Inject constructor(
    private val llmDataSource: LLMRemoteDataSource
): LLMRepository {
    override suspend fun convertImageToText(list: List<ImageToConvert>): ConvertedImageResult {
        return llmDataSource.convertImageToText(list).mapToDomainModel()
    }
}