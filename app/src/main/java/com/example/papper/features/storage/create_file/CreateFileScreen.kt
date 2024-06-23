package com.example.papper.features.storage.create_file

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
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
            navHostController = navHostController,
        )
    }

    CreateFileBasic(
        modifier = modifier,
        viewModel = viewModel,
    )
}

private fun handleSideEffects(
    sideEffect: CreateFileSideEffects,
    navHostController: NavHostController,
) {
    when (sideEffect) {
        CreateFileSideEffects.NavigateBack -> {
            navHostController.popBackStack()
        }
        is CreateFileSideEffects.NavigatePopBack -> {
            navHostController.previousBackStackEntry?.savedStateHandle?.set(
                "title", sideEffect.title
            )
            navHostController.previousBackStackEntry?.savedStateHandle?.set(
                "text", sideEffect.text
            )
            navHostController.popBackStack()
        }
    }
}