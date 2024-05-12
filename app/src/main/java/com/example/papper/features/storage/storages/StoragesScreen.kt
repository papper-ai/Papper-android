package com.example.papper.features.storage.storages

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.papper.features.storage.storages.presentation.StoragesScreenState
import com.example.papper.features.storage.storages.presentation.StoragesSideEffects
import com.example.papper.features.storage.storages.presentation.StoragesViewModel
import com.example.papper.navigation.Screens
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun StoragesScreen(
    modifier: Modifier = Modifier,
    viewModel: StoragesViewModel,
    navHostController: NavHostController,
) {
    viewModel.collectSideEffect { sideEffect ->
        handleSideEffects(
            viewModel = viewModel,
            sideEffect = sideEffect,
            navHostController = navHostController,
        )
    }

    StorageScreenBasic(
        modifier = modifier,
        viewModel = viewModel,
        navHostController = navHostController,
    )
}

private fun handleSideEffects(
    viewModel: StoragesViewModel,
    sideEffect: StoragesSideEffects,
    navHostController: NavHostController,
) {
    when(sideEffect) {
        StoragesSideEffects.ShowLoading -> {
            viewModel.storagesScreenState.value = StoragesScreenState.Loading
        }
        StoragesSideEffects.ShowSuccess -> {
            viewModel.storagesScreenState.value = StoragesScreenState.Success
        }
        StoragesSideEffects.ShowError -> {
            viewModel.storagesScreenState.value = StoragesScreenState.Error
        }
        is StoragesSideEffects.NavigateToCreateChatScreen -> {
            navHostController.previousBackStackEntry?.savedStateHandle?.set("storageId", sideEffect.id)
            navHostController.popBackStack()
        }
        is StoragesSideEffects.NavigateToStorageScreen -> {
            navHostController.navigate(Screens.StorageScreen.route + "/${sideEffect.id}")
        }
        StoragesSideEffects.NavigateToCreateStorageScreen -> {
            navHostController.navigate(Screens.CreateStorageScreen.route)
        }
    }
}