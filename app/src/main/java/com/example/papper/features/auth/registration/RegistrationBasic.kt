package com.example.papper.features.auth.registration

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.papper.features.auth.registration.presentation.RegistrationScreenState
import com.example.papper.features.auth.registration.presentation.RegistrationViewModel
import com.example.papper.features.auth.registration.view.all_fields.AllFieldBasic
import com.example.papper.features.auth.registration.view.code.CodeBasic
import com.example.papper.features.auth.registration.view.login.LoginBasic
import com.example.papper.features.auth.registration.view.name.NameBasic
import com.example.papper.features.auth.registration.view.password.PasswordBasic
import com.example.papper.features.auth.registration.view.surname.SurnameBasic

@Composable
fun RegistrationBasic(
    modifier: Modifier = Modifier,
    viewModel: RegistrationViewModel,
    navHostController: NavHostController
) {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.background,
    ) {
        when (viewModel.registrationScreenState.value) {
            is RegistrationScreenState.TypingName -> NameBasic(
                modifier = modifier,
                viewModel = viewModel
            )

            is RegistrationScreenState.TypingSurname -> SurnameBasic(
                modifier = modifier,
                viewModel = viewModel
            )

            is RegistrationScreenState.TypingLogin -> LoginBasic(
                modifier = modifier,
                viewModel = viewModel
            )

            is RegistrationScreenState.TypingPassword -> PasswordBasic(
                modifier = modifier,
                viewModel = viewModel
            )

            is RegistrationScreenState.TypingCode -> CodeBasic(
                modifier = modifier,
                viewModel = viewModel
            )

            is RegistrationScreenState.AllFields -> AllFieldBasic(
                modifier = modifier,
                viewModel = viewModel,
                navHostController = navHostController,
            )

            is RegistrationScreenState.Error -> TODO()
            is RegistrationScreenState.Loading -> TODO()
            is RegistrationScreenState.Success -> TODO()
        }
    }

}