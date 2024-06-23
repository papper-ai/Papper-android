package com.example.papper.features.profile

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.example.papper.R
import com.example.papper.features.profile.presentation.ProfileScreenState
import com.example.papper.features.profile.presentation.ProfileSideEffects
import com.example.papper.features.profile.presentation.ProfileViewModel
import com.example.papper.navigation.ChatsScreen
import com.example.papper.navigation.ProfileScreen
import com.example.papper.navigation.StartScreen
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel,
    navHostController: NavHostController,
) {
    val context = LocalContext.current

    viewModel.collectSideEffect { sideEffect ->
        handleSideEffects(
            viewModel = viewModel,
            sideEffect = sideEffect,
            navHostController = navHostController,
            context = context,
        )
    }

    ProfileBasic(
        modifier = modifier,
        viewModel = viewModel,
    )
}

private fun handleSideEffects(
    viewModel: ProfileViewModel,
    sideEffect: ProfileSideEffects,
    navHostController: NavHostController,
    context: Context,
) {
    when (sideEffect) {
        ProfileSideEffects.ShowLoading -> {
            viewModel.profileScreenState.value = ProfileScreenState.Loading
        }
        ProfileSideEffects.ShowSuccess -> {
            viewModel.profileScreenState.value = ProfileScreenState.Success
        }
        ProfileSideEffects.ShowError -> {
            viewModel.profileScreenState.value = ProfileScreenState.Error
        }
        ProfileSideEffects.ShowNetworkConnectionError -> {
            viewModel.profileScreenState.value = ProfileScreenState.Error
            Toast.makeText(context, context.getText(R.string.network_connection_error), Toast.LENGTH_SHORT).show()
        }
        ProfileSideEffects.NavigateToChatsScreen -> {
            navHostController.popBackStack()
        }

        ProfileSideEffects.NavigateToStartScreen -> {
            navHostController.navigate(
                StartScreen,
            ) {
                popUpTo(ProfileScreen) {
                    inclusive = true
                }
                popUpTo(ChatsScreen) {
                    inclusive = true
                }
                launchSingleTop = true
                restoreState = true
            }
        }
    }
}