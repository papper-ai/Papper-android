package com.example.papper.features.auth.sign_in.presentation

data class SignInState(
    val login: String = "",
    val password: String = "",
)

sealed class SignInScreenState {
    object Default: SignInScreenState()
    object Loading: SignInScreenState()
    object Error: SignInScreenState()
}