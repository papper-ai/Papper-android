package com.example.papper.features.auth.registration.presentation

sealed class RegistrationSideEffects {
    object toSurname : RegistrationSideEffects()
    object toLogin : RegistrationSideEffects()
    object toPassword : RegistrationSideEffects()
    object toCode : RegistrationSideEffects()
    object toAllFields : RegistrationSideEffects()
    object NavigateToChatsScreen : RegistrationSideEffects()

//    object ShowLoadingState : RegistrationSideEffects()
//    object ShowErrorState : RegistrationSideEffects()
}