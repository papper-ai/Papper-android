package com.example.domain.repository

import com.example.domain.model.llm.ConvertedImageResult
import com.example.domain.model.llm.ImageToConvert

interface LLMRepository {
    suspend fun convertImageToText(list: List<ImageToConvert>): ConvertedImageResult
}