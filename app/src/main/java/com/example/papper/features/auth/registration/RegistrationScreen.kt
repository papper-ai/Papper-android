package com.example.papper.features.auth.registration

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.example.papper.R
import com.example.papper.features.archive.presentation.ArchivesViewModel
import com.example.papper.features.auth.registration.presentation.RegistrationScreenState
import com.example.papper.features.auth.registration.presentation.RegistrationSideEffects
import com.example.papper.features.auth.registration.presentation.RegistrationViewModel
import com.example.papper.features.chat.chats.presentation.ChatsViewModel
import com.example.papper.features.storage.storages.presentation.StoragesViewModel
import com.example.papper.navigation.Screens
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun RegistrationScreen(
    viewModel: RegistrationViewModel,
    chatsViewModel: ChatsViewModel,
    storagesViewModel: StoragesViewModel,
    archivesViewModel: ArchivesViewModel,
    navHostController: NavHostController,
) {
    val context = LocalContext.current

    viewModel.collectSideEffect { sideEffect ->
        handleSideEffects (
            viewModel = viewModel,
            chatsViewModel = chatsViewModel,
            storagesViewModel = storagesViewModel,
            archivesViewModel = archivesViewModel,
            sideEffect = sideEffect,
            navHostController = navHostController,
            context = context,
        )
    }
    RegistrationBasic(viewModel = viewModel, navHostController = navHostController)
}

private fun handleSideEffects(
    viewModel: RegistrationViewModel,
    chatsViewModel: ChatsViewModel,
    storagesViewModel: StoragesViewModel,
    archivesViewModel: ArchivesViewModel,
    sideEffect: RegistrationSideEffects,
    navHostController: NavHostController,
    context: Context,
) {
    when (sideEffect) {
//        is RegistrationSideEffects.toSurname -> {
//            viewModel.registrationScreenState.value = RegistrationScreenState.TypingSurname
//        }
//        is RegistrationSideEffects.toLogin -> {
//            viewModel.registrationScreenState.value = RegistrationScreenState.TypingLogin
//        }
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
            chatsViewModel.loadData()
            storagesViewModel.loadData()
            archivesViewModel.loadData()
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
        RegistrationSideEffects.ShowNetworkConnectionError -> {
            Toast.makeText(context, context.getText(R.string.network_connection_error), Toast.LENGTH_SHORT).show()
        }
    }
}