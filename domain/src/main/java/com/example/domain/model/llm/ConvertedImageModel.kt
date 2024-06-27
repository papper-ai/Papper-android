package com.example.domain.model.llm

data class ConvertedImageResult (
    val isSuccess: Boolean,
    val msg: String,
    val list: List<ConvertedImageModel>,
)

data class ImageToConvert (
    val uuid: String,
    val image: String,
)

data class ConvertedImageModel (
    val uuid: String,
    val text: String,
)