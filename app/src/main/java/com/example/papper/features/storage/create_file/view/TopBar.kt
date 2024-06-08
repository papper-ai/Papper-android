package com.example.papper.features.storage.create_file.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.papper.features.common.components.TopBarWithLogoComponent
import com.example.papper.features.storage.create_file.presentation.CreateFileScreenState
import com.example.papper.features.storage.create_file.presentation.CreateFileViewModel

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    viewModel: CreateFileViewModel,
    navHostController: NavHostController,
) {
    TopBarWithLogoComponent(
        modifier = modifier,
        onClick = {
            when (viewModel.createFileScreenState.value) {
                CreateFileScreenState.TypingTitle -> {
                    navHostController.popBackStack()
                }

                CreateFileScreenState.AttachPhotos -> {
                    viewModel.createFileScreenState.value = CreateFileScreenState.TypingTitle
                }

                CreateFileScreenState.ConfirmCreating -> {
                    viewModel.createFileScreenState.value = CreateFileScreenState.AttachPhotos
                }

                CreateFileScreenState.Error -> {
                    viewModel.createFileScreenState.value = CreateFileScreenState.ConfirmCreating
                }
            }
        },
    )
}