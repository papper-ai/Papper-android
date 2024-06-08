package com.example.papper.features.storage.create_file

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.papper.features.storage.create_file.presentation.CreateFileScreenState
import com.example.papper.features.storage.create_file.presentation.CreateFileSideEffects
import com.example.papper.features.storage.create_file.presentation.CreateFileViewModel
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun CreateFileScreen(
    modifier: Modifier = Modifier,
    viewModel: CreateFileViewModel,
    navHostController: NavHostController,
) {
    viewModel.collectSideEffect { sideEffect ->
        handleSideEffects(
            sideEffect = sideEffect,
            viewModel = viewModel,
            navHostController = navHostController
        )
    }

    CreateFileBasic(
        modifier = modifier,
        viewModel = viewModel,
        navHostController = navHostController
    )
}

private fun handleSideEffects(
    sideEffect: CreateFileSideEffects,
    viewModel: CreateFileViewModel,
    navHostController: NavHostController,
) {
    when (sideEffect) {
        CreateFileSideEffects.ShowAttachPhotos -> {
            viewModel.createFileScreenState.value = CreateFileScreenState.AttachPhotos
        }
        CreateFileSideEffects.ShowConfirmCreating -> {
            viewModel.createFileScreenState.value = CreateFileScreenState.ConfirmCreating
        }
    }
}