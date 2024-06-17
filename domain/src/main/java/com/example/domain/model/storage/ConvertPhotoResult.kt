package com.example.domain.model.storage

data class ConvertPhotoResult (
    val isSuccess: Boolean,
    val code: String,
    val msg: String,
    val list: List<ConvertedPhotoDomain>
)

data class ConvertedPhotoDomain(
    val id: Int,
    val text: String,
)