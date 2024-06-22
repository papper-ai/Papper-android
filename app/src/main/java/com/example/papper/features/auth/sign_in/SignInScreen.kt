package com.example.papper.features.auth.sign_in

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.example.papper.R
import com.example.papper.features.archive.presentation.ArchivesViewModel
import com.example.papper.features.auth.sign_in.presentation.SignInScreenState
import com.example.papper.features.auth.sign_in.presentation.SignInSideEffects
import com.example.papper.features.auth.sign_in.presentation.SignInViewModel
import com.example.papper.features.chat.chats.presentation.ChatsViewModel
import com.example.papper.features.storage.storages.presentation.StoragesViewModel
import com.example.papper.navigation.ChatsScreen
import com.example.papper.navigation.StartScreen
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun SignInScreen(
    modifier: Modifier = Modifier,
    viewModel: SignInViewModel,
    chatsViewModel: ChatsViewModel,
    storagesViewModel: StoragesViewModel,
    archivesViewModel: ArchivesViewModel,
    navHostController: NavHostController,
) {
    val context = LocalContext.current

    viewModel.collectSideEffect { sideEffect ->
        handleSideEffects(
            sideEffects = sideEffect,
            viewModel = viewModel,
            chatsViewModel = chatsViewModel,
            storagesViewModel = storagesViewModel,
            archivesViewModel = archivesViewModel,
            navHostController = navHostController,
            context = context,
        )
    }
    SignInBasic(viewModel = viewModel)
}

private fun handleSideEffects(
    sideEffects: SignInSideEffects,
    viewModel: SignInViewModel,
    chatsViewModel: ChatsViewModel,
    storagesViewModel: StoragesViewModel,
    archivesViewModel: ArchivesViewModel,
    navHostController: NavHostController,
    context: Context,
) {
    when (sideEffects) {
        is SignInSideEffects.ShowErrorState -> {
            viewModel.signInScreenState.value = SignInScreenState.Error
            Toast.makeText(context, sideEffects.msg, Toast.LENGTH_SHORT).show()
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
        SignInSideEffects.NavigateToChatsScreen -> {
            chatsViewModel.loadData()
            storagesViewModel.loadData()
            archivesViewModel.loadData()
            navHostController.navigate(
                ChatsScreen,
            ) {
                popUpTo(StartScreen) {
                    inclusive = true
                }
                launchSingleTop = true
                restoreState = true
            }
        }
        SignInSideEffects.ShowNetworkConnectionError -> {
            viewModel.signInScreenState.value = SignInScreenState.Default
            Toast.makeText(context, context.getText(R.string.network_connection_error), Toast.LENGTH_SHORT).show()
        }
    }
}