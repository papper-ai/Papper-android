package com.example.papper.features.auth.sign_in

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.papper.features.auth.sign_in.presentation.SignInScreenState
import com.example.papper.features.auth.sign_in.presentation.SignInSideEffects
import com.example.papper.features.auth.sign_in.presentation.SignInViewModel
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun SignInScreen(
    modifier: Modifier = Modifier,
    viewModel: SignInViewModel,
    navHostController: NavHostController,
) {
    viewModel.collectSideEffect { sideEffect ->
        handleSideEffects(
            sideEffects = sideEffect,
            viewModel = viewModel
        )
    }
    SignInBasic(viewModel = viewModel, navHostController = navHostController)
}

private fun handleSideEffects(
    sideEffects: SignInSideEffects,
    viewModel: SignInViewModel
) {
    when (sideEffects) {
        is SignInSideEffects.ShowErrorState -> {
            viewModel.signInScreenState.value = SignInScreenState.Error
        }
        is SignInSideEffects.ShowLoadingState -> {
            viewModel.signInScreenState.value = SignInScreenState.Loading
        }
        is SignInSideEffects.ForgotPasswordClick -> {
            TODO()
        }
        is SignInSideEffects.FieldsFilled -> {
            viewModel.fieldsStatus.value = sideEffects.status
        }
    }
}