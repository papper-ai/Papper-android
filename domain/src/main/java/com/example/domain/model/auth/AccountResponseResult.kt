package com.example.domain.model.auth

data class AccountResponseResult(
    val isSuccess: Boolean,
    val code: String,
    val msg: String,
)