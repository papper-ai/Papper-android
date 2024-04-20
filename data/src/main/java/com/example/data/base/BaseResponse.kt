package com.example.data.base

data class BaseResponse (
    val isSuccess: Boolean,
    val code: String,
    val msg: String,
)