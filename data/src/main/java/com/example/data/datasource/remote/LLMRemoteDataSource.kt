package com.example.data.datasource.remote

import android.util.Log
import com.example.data.api.LLMApiService
import com.example.data.base.BaseResponse
import com.example.data.model.llm.ImageToTextBody
import com.example.data.model.llm.ImageToTextResponseResult
import com.example.domain.model.llm.ImageToConvert
import okio.IOException
import javax.inject.Inject

class LLMRemoteDataSource @Inject constructor(
    private val apiService: LLMApiService
) {
    suspend fun convertImageToText(list: List<ImageToConvert>): ImageToTextResponseResult {
        lateinit var result: ImageToTextResponseResult

        val images = mutableListOf<ImageToTextBody>()
        for (item in list) {
            images.add(
                ImageToTextBody(
                    uuid = item.uuid,
                    image = item.image
                )
            )
        }

        try {
            val resultFromApi = apiService.convertImageToText(requestBody = images)

            if (resultFromApi.isSuccessful) {
                result = ImageToTextResponseResult (
                    baseResponse = BaseResponse(
                        isSuccess = true,
                        code = resultFromApi.code().toString(),
                        msg = resultFromApi.message(),
                    ),
                    list = resultFromApi.body()!!
                )
            } else {
                result = ImageToTextResponseResult(
                    baseResponse = BaseResponse(
                        isSuccess = false,
                        code = "0",
                        msg = "Ошибка подключения к интернету"
                    ),
                    list = emptyList()
                )
            }
        } catch (e: IOException) {
            result = ImageToTextResponseResult(
                baseResponse = BaseResponse(
                    isSuccess = false,
                    code = "0",
                    msg = "Ошибка подключения к интернету"
                ),
                list = emptyList()
            )
        }
        catch (e: Exception) {
            result = ImageToTextResponseResult(
                baseResponse = BaseResponse(
                    isSuccess = false,
                    code = "0",
                    msg = "Неизвестная ошибка"
                ),
                list = emptyList()
            )
        }

        return result
    }
}