package com.example.papper.features.auth.registration.presentation

data class RegistrationState(
    val name: String = "",
    val surname: String = "",
    val login: String = "",
    val password: String = "",
    val code: String = "",
)

sealed class RegistrationScreenState {
    object TypingName: RegistrationScreenState()
    object TypingSurname: RegistrationScreenState()
    object TypingLogin: RegistrationScreenState()
    object TypingPassword: RegistrationScreenState()
    object TypingCode: RegistrationScreenState()
    object AllFields: RegistrationScreenState()

}

sealed class AllFieldsScreenState {
    object Default: AllFieldsScreenState()
    object Loading: AllFieldsScreenState()
    object Error: AllFieldsScreenState()

}
