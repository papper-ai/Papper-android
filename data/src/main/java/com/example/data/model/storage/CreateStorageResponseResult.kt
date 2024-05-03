package com.example.data.model.storage

import com.example.data.base.BaseResponse
import com.example.domain.model.storage.CreateStorageResult

data class CreateStorageResponse(
    val id: String,
//    val name: String,
//    val type: String,
//    val created_at: String,
//    val user_id: String,
//    val documents: List<Document>
)

//data class Document(
//    val id: String,
//    val name: String,
//    val text: String,
//    val vault_id: String
//)

data class CreateStorageResponseResult(
    val baseResponse: BaseResponse,
    val id: String,
)

internal fun CreateStorageResponseResult.mapToDomainModel() : CreateStorageResult =
    CreateStorageResult(
        isSuccess = baseResponse.isSuccess,
        code = baseResponse.code,
        msg = baseResponse.msg,
        id = id
    )