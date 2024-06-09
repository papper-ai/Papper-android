package com.example.papper.features.auth.start

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.example.papper.R
import com.example.papper.features.archive.presentation.ArchivesViewModel
import com.example.papper.features.auth.start.presentation.StartSideEffects
import com.example.papper.features.auth.start.presentation.StartViewModel
import com.example.papper.features.chat.chats.presentation.ChatsViewModel
import com.example.papper.features.storage.storages.presentation.StoragesViewModel
import com.example.papper.navigation.Screens
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun StartScreen(
    modifier: Modifier = Modifier,
    viewModel: StartViewModel,
    chatsViewModel: ChatsViewModel,
    storagesViewModel: StoragesViewModel,
    archivesViewModel: ArchivesViewModel,
    navHostController: NavHostController,
) {
    val context = LocalContext.current

    viewModel.collectSideEffect { sideEffect ->
        handleSideEffects(
            sideEffect = sideEffect,
            chatsViewModel = chatsViewModel,
            storagesViewModel = storagesViewModel,
            archivesViewModel = archivesViewModel,
            navHostController = navHostController,
            context = context,
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
    chatsViewModel: ChatsViewModel,
    storagesViewModel: StoragesViewModel,
    archivesViewModel: ArchivesViewModel,
    navHostController: NavHostController,
    context: Context,
) {
    when (sideEffect) {
        StartSideEffects.NavigateToChatsScreen -> {
            chatsViewModel.loadData()
            storagesViewModel.loadData()
            archivesViewModel.loadData()
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

        StartSideEffects.ShowNetworkConnectionError -> {
            Toast.makeText(context, context.getText(R.string.network_connection_error), Toast.LENGTH_SHORT).show()
        }
    }
}