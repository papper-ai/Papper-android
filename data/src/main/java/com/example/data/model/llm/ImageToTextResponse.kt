package com.example.data.model.llm

import com.example.data.base.BaseResponse
import com.example.domain.model.llm.ConvertedImageModel
import com.example.domain.model.llm.ConvertedImageResult

data class ImageToTextBody (
    val uuid: String,
    val image: String,
)

data class ConvertedImage(
    val uuid: String,
    val text: String,
)

data class ImageToTextResponseResult (
    val baseResponse: BaseResponse,
    val list: List<ConvertedImage>
)

fun ImageToTextResponseResult.mapToDomainModel() : ConvertedImageResult {
    return ConvertedImageResult(
        isSuccess = baseResponse.isSuccess,
        msg = baseResponse.msg,
        list = list.map { convertedImage ->
            ConvertedImageModel(
                uuid = convertedImage.uuid,
                text = convertedImage.text,
            )
        }
    )
}