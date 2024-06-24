package com.example.data.model.storage

import com.example.data.base.BaseResponse
import com.example.domain.model.storage.AddFileInStorageResult
import com.google.gson.annotations.SerializedName
import okhttp3.MultipartBody

data class AddFileIntoStorageResponse(
    val id: String,
    val text: String,
)

data class AddFileInStorageResponseResult(
    val baseResponse: BaseResponse,
    val id: String,
    val text: String,
)

fun AddFileInStorageResponseResult.mapToDomainModel(): AddFileInStorageResult = AddFileInStorageResult(
    id = id,
    isSuccess = baseResponse.isSuccess,
    code = baseResponse.code,
    msg = baseResponse.msg,
    text = text,
)