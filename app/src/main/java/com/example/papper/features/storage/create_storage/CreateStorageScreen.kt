package com.example.papper.features.storage.create_storage

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.papper.features.storage.create_storage.presentation.CreateStorageScreenState
import com.example.papper.features.storage.create_storage.presentation.CreateStorageSideEffects
import com.example.papper.features.storage.create_storage.presentation.CreateStorageViewModel
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun CreateStoragesScreen(
    modifier: Modifier = Modifier,
    viewModel: CreateStorageViewModel,
    navHostController: NavHostController,
) {
    viewModel.collectSideEffect { sideEffect ->
        handleSideEffect(
            viewModel = viewModel,
            sideEffect = sideEffect
        )
    }
    CreateStorageBasic(
        modifier = modifier,
        viewModel = viewModel,
        navHostController = navHostController
    )
}

private fun handleSideEffect(
    viewModel: CreateStorageViewModel,
    sideEffect: CreateStorageSideEffects,
) {
    when (sideEffect) {
        CreateStorageSideEffects.ShowAttachFilesScreen -> {
            viewModel.createStorageScreenState.value = CreateStorageScreenState.AttachFiles
        }
    }
}