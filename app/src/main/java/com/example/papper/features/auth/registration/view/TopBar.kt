package com.example.papper.features.auth.registration.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.papper.features.auth.registration.presentation.RegistrationScreenState
import com.example.papper.features.auth.registration.presentation.RegistrationViewModel
import com.example.papper.features.common.components.TopBarWithLogoComponent

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    viewModel: RegistrationViewModel,
    navHostController: NavHostController,
) {
    TopBarWithLogoComponent(
        modifier = modifier,
        onClick = {
            when (viewModel.registrationScreenState.value) {
                RegistrationScreenState.TypingName -> {
                    navHostController.popBackStack()
                }

                RegistrationScreenState.TypingSurname -> {
                    viewModel.registrationScreenState.value = RegistrationScreenState.TypingName
                }

                RegistrationScreenState.TypingLogin -> {
                    viewModel.registrationScreenState.value = RegistrationScreenState.TypingSurname
                }

                RegistrationScreenState.TypingPassword -> {
                    viewModel.registrationScreenState.value = RegistrationScreenState.TypingLogin
                }

                RegistrationScreenState.TypingCode -> {
                    viewModel.registrationScreenState.value = RegistrationScreenState.TypingPassword
                }

                RegistrationScreenState.AllFields -> {
                    viewModel.registrationScreenState.value = RegistrationScreenState.TypingCode
                }

//                RegistrationScreenState.Error -> {
//                    viewModel.registrationScreenState.value = RegistrationScreenState.AllFields
//                }
//
//                RegistrationScreenState.Loading -> {
//                    TODO()
//                }

//                RegistrationScreenState.Success -> {
//                    TODO()
//                }

            }
        }
    )
}