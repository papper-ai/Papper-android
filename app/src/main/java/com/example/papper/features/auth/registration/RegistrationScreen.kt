package com.example.papper.features.auth.registration

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.papper.features.auth.registration.presentation.RegistrationScreenState
import com.example.papper.features.auth.registration.presentation.RegistrationSideEffects
import com.example.papper.features.auth.registration.presentation.RegistrationViewModel
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun RegistrationScreen(
    viewModel: RegistrationViewModel,
    navHostController: NavHostController,
) {
    viewModel.collectSideEffect { sideEffect ->
        handleSideEffects (
            viewModel = viewModel,
            sideEffect = sideEffect,
        )
    }
    RegistrationBasic(viewModel = viewModel, navHostController = navHostController)
}

private fun handleSideEffects(
    viewModel: RegistrationViewModel,
    sideEffect: RegistrationSideEffects
) {
    when (sideEffect) {
        is RegistrationSideEffects.toSurname -> {
            viewModel.registrationScreenState.value = RegistrationScreenState.TypingSurname
        }
        is RegistrationSideEffects.toLogin -> {
            viewModel.registrationScreenState.value = RegistrationScreenState.TypingLogin
        }
        is RegistrationSideEffects.toPassword -> {
            viewModel.registrationScreenState.value = RegistrationScreenState.TypingPassword
        }
        is RegistrationSideEffects.toCode -> {
            viewModel.registrationScreenState.value = RegistrationScreenState.TypingCode
        }
        is RegistrationSideEffects.toAllFields -> {
            viewModel.registrationScreenState.value = RegistrationScreenState.AllFields
        }
        is RegistrationSideEffects.ShowLoadingState -> {
            TODO()
        }
        is RegistrationSideEffects.ShowErrorState -> {
            TODO()
        }

    }
}