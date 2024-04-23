package com.example.papper.features.auth.start

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.papper.features.auth.start.presentation.StartSideEffects
import com.example.papper.features.auth.start.presentation.StartViewModel
import com.example.papper.navigation.Screens
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun StartScreen(
    modifier: Modifier = Modifier,
    viewModel: StartViewModel,
    navHostController: NavHostController,
) {
    viewModel.collectSideEffect { sideEffect ->
        handleSideEffects(
            sideEffect = sideEffect,
            navHostController = navHostController,
        )
    }

    StartBasic(
        modifier = modifier,
        viewModel = viewModel,
        navHostController = navHostController,
    )
}

private fun handleSideEffects(
    sideEffect: StartSideEffects,
    navHostController: NavHostController,
) {
    when (sideEffect) {
        StartSideEffects.NavigateToChatsScreen -> {
            navHostController.navigate(
                Screens.ChatsScreen.route,
            ) {
                popUpTo(Screens.StartScreen.route) {
                    inclusive = true
                }
                launchSingleTop = true
                restoreState = true
            }
        }
        StartSideEffects.NavigateToRegistrationScreen -> {
            navHostController.navigate(Screens.RegistrationScreen.route)
        }
        StartSideEffects.NavigateToSignInScreen -> {
            navHostController.navigate(Screens.SignInScreen.route)
        }
    }
}