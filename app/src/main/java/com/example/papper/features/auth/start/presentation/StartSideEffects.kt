package com.example.papper.features.auth.start.presentation

sealed class StartSideEffects {
    object NavigateToChatsScreen : StartSideEffects()
    object NavigateToRegistrationScreen : StartSideEffects()
    object NavigateToSignInScreen : StartSideEffects()
}