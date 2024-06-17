package com.example.papper.features.storage.create_file.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.papper.features.common.components.TopBarWithLogoComponent
import com.example.papper.features.storage.create_file.presentation.CreateFileScreenState
import com.example.papper.features.storage.create_file.presentation.CreateFileViewModel
import com.example.papper.features.storage.create_file.view.confirm_creating.ConfirmExitAlertDialog

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    viewModel: CreateFileViewModel,
    navHostController: NavHostController,
) {
    var confirmExit by remember {
        mutableStateOf(false)
    }

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
                    confirmExit = true
                }

                CreateFileScreenState.Error -> {
                    viewModel.createFileScreenState.value = CreateFileScreenState.ConfirmCreating
                }
            }
        },
    )

    ConfirmExitAlertDialog(
        onDismiss = { confirmExit = false },
        viewModel = viewModel,
        showDialog = confirmExit,
    )
}