package com.example.data.api

import com.example.data.model.llm.ConvertedImage
import com.example.data.model.llm.ImageToTextBody
import com.example.data.utils.Constants
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface LLMApiService {

    @POST(Constants.OCR)
    @Headers("Content-Type: application/json", "accept: application/json")
    suspend fun convertImageToText(
        @Body requestBody: List<ImageToTextBody>
    ): Response<List<ConvertedImage>>

}