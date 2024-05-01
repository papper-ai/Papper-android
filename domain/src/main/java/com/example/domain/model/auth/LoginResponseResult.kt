package com.example.domain.model.auth

data class LoginResponseResult (
    val isSuccess: Boolean,
    val code: String,
    val msg: String,
    val login: String,
)