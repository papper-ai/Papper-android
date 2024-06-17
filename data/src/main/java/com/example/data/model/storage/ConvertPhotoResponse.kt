package com.example.data.model.storage

import com.example.data.base.BaseResponse
import com.example.domain.model.storage.ConvertPhotoResult
import com.example.domain.model.storage.ConvertedPhotoDomain

data class ConvertPhotoResponseResult(
    val baseResponse: BaseResponse,
    val list: List<ConvertedPhoto>
)

data class ConvertedPhoto(
    val id: Int,
    val text: String,
)

internal fun ConvertPhotoResponseResult.mapToDomainModel() : ConvertPhotoResult =
    ConvertPhotoResult(
        isSuccess = baseResponse.isSuccess,
        code = baseResponse.code,
        msg = baseResponse.msg,
        list = list.map { photo ->
            ConvertedPhotoDomain(
                id = photo.id,
                text = photo.text
            )
        }
    )