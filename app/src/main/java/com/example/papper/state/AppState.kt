package com.example.papper.state

import com.example.papper.features.auth.registration.presentation.RegistrationState
import com.example.papper.features.auth.sign_in.presentation.SignInState

data class AppState(
    val signInState: SignInState = SignInState(),
    val registrationState: RegistrationState = RegistrationState(),
)