package com.example.papper.features.auth.registration

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.papper.features.auth.registration.presentation.RegistrationScreenState
import com.example.papper.features.auth.registration.presentation.RegistrationViewModel
import com.example.papper.features.auth.registration.view.TopBar
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
    navHostController: NavHostController,
) {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.background,
    ) {
        when (viewModel.registrationScreenState.value) {
            is RegistrationScreenState.TypingName -> {
                Scaffold(
                    topBar = {
                        TopBar(
                            viewModel = viewModel,
                            navHostController = navHostController,
                        )
                    },
                    content = {
                        NameBasic(
                            modifier = modifier.padding(it),
                            viewModel = viewModel,
                        )
                    }
                )
            }

            is RegistrationScreenState.TypingSurname -> {
                Scaffold(
                    topBar = {
                        TopBar(
                            viewModel = viewModel,
                            navHostController = navHostController,
                        )
                    },
                    content = {
                        SurnameBasic(
                            modifier = modifier.padding(it),
                            viewModel = viewModel,
                        )
                    }
                )
            }

            is RegistrationScreenState.TypingLogin -> {
                Scaffold(
                    topBar = {
                        TopBar(
                            viewModel = viewModel,
                            navHostController = navHostController,
                        )
                    },
                    content = {
                        LoginBasic(
                            modifier = modifier.padding(it),
                            viewModel = viewModel,
                        )
                    }
                )
            }

            is RegistrationScreenState.TypingPassword -> {
                Scaffold(
                    topBar = {
                        TopBar(
                            viewModel = viewModel,
                            navHostController = navHostController,
                        )
                    },
                    content = {
                        PasswordBasic(
                            modifier = modifier.padding(it),
                            viewModel = viewModel,
                        )
                    }
                )
            }

            is RegistrationScreenState.TypingCode -> {
                Scaffold(
                    topBar = {
                        TopBar(
                            viewModel = viewModel,
                            navHostController = navHostController,
                        )
                    },
                    content = {
                        CodeBasic(
                            modifier = modifier.padding(it),
                            viewModel = viewModel,
                        )
                    }
                )
            }

            is RegistrationScreenState.AllFields -> {
                AllFieldBasic(
                    modifier = modifier,
                    viewModel = viewModel,
                    navHostController = navHostController,
                )
            }

//            is RegistrationScreenState.Error -> {
//                Scaffold(
//                    topBar = {
//                        TopBar(
//                            viewModel = viewModel,
//                            navHostController = navHostController,
//                        )
//                    },
//                    content = {
//                        ErrorBasic(
//                            modifier = modifier.padding(it),
//                            viewModel = viewModel,
//                        )
//                    }
//                )
//            }
//
//            is RegistrationScreenState.Loading -> {}
//            is RegistrationScreenState.Success -> {}
        }
    }
}