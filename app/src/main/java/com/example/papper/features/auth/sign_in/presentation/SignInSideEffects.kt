package com.example.papper.features.auth.sign_in.presentation

sealed class SignInSideEffects {
    object ForgotPasswordClick: SignInSideEffects()
    data class FieldsFilled(val status: Boolean): SignInSideEffects()
    object ShowLoadingState: SignInSideEffects()
    data class ShowErrorState(val msg: String): SignInSideEffects()
    object NavigateToChatsScreen: SignInSideEffects()
}
