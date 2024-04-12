package com.example.papper.features.storage.storages

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.papper.features.storage.storages.presentation.StoragesScreenState
import com.example.papper.features.storage.storages.presentation.StoragesSideEffects
import com.example.papper.features.storage.storages.presentation.StoragesViewModel
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
        )
    }

    StorageScreenBasic(
        modifier = modifier,
        viewModel = viewModel,
        navHostController = navHostController
    )
}

private fun handleSideEffects(
    viewModel: StoragesViewModel,
    sideEffect: StoragesSideEffects
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
    }
}