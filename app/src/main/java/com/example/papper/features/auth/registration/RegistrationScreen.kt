package com.example.papper.features.auth.registration

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.papper.features.auth.registration.presentation.RegistrationScreenState
import com.example.papper.features.auth.registration.presentation.RegistrationSideEffects
import com.example.papper.features.auth.registration.presentation.RegistrationViewModel
import com.example.papper.navigation.Screens
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
            navHostController = navHostController,
        )
    }
    RegistrationBasic(viewModel = viewModel, navHostController = navHostController)
}

private fun handleSideEffects(
    viewModel: RegistrationViewModel,
    sideEffect: RegistrationSideEffects,
    navHostController: NavHostController,
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

        is RegistrationSideEffects.NavigateToChatsScreen -> {
            navHostController.navigate(
                Screens.ChatsScreen.route,
            ) {
                popUpTo(Screens.RegistrationScreen.route) {
                    inclusive = true
                }
                popUpTo(Screens.StartScreen.route) {
                    inclusive = true
                }
                launchSingleTop = true
                restoreState = true
            }
        }
    }
}