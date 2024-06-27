package com.example.domain.usecases.llm

import com.example.domain.model.llm.ConvertedImageResult
import com.example.domain.model.llm.ImageToConvert
import com.example.domain.repository.LLMRepository
import javax.inject.Inject

class ConvertImageToTextUseCase @Inject constructor(
    private val repository: LLMRepository,
) {
    suspend fun execute(list: List<ImageToConvert>): ConvertedImageResult {
        return repository.convertImageToText(list)
    }
}