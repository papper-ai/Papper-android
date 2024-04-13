package com.example.papper.features.storage.storage

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.papper.features.storage.storage.presentation.StorageScreenState
import com.example.papper.features.storage.storage.presentation.StorageSideEffects
import com.example.papper.features.storage.storage.presentation.StorageViewModel
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun StorageScreen(
    modifier: Modifier = Modifier,
    viewModel: StorageViewModel,
    navHostController: NavHostController,
    id: String,
) {
    viewModel.getData(id = id)

    viewModel.collectSideEffect { sideEffect ->
        handleSideEffect(
            viewModel = viewModel,
            sideEffect = sideEffect
        )
    }
    StorageBasic(
        modifier = modifier,
        viewModel = viewModel,
        navHostController = navHostController
    )
}

private fun handleSideEffect(
    viewModel: StorageViewModel,
    sideEffect: StorageSideEffects
) {
    when (sideEffect) {
        StorageSideEffects.ShowLoading -> {
            viewModel.storageScreenState.value = StorageScreenState.Loading
        }
        StorageSideEffects.ShowSuccess -> {
            viewModel.storageScreenState.value = StorageScreenState.Success
        }
        StorageSideEffects.ShowError -> {
            viewModel.storageScreenState.value = StorageScreenState.Error
        }
    }
}