package com.example.papper.features.auth.sign_in_by_face.presentation

sealed class SignInByFaceSideEffects {
    object Success : SignInByFaceSideEffects()
    object NavigateToChatsScreen : SignInByFaceSideEffects()
    object ShowNetworkConnectionError : SignInByFaceSideEffects()
}