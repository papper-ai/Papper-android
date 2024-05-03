package com.example.data.model.storage

import com.example.data.base.BaseResponse
import com.google.gson.annotations.SerializedName

data class RenameVaultResponseResult(
    val baseResponse: BaseResponse
)

data class RenameVaultBody(
    @SerializedName("vault_id")
    val vaultId: String,
    @SerializedName("name")
    val title: String,
)

internal fun RenameVaultResponseResult.mapToDomainModel() = com.example.domain.model.BaseResponse(
    isSuccess = baseResponse.isSuccess,
    msg = baseResponse.msg
)