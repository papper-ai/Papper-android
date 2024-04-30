package com.example.papper.features.storage.create_storage.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.papper.features.common.components.TopBarWithLogoComponent
import com.example.papper.features.storage.create_storage.presentation.CreateStorageScreenState
import com.example.papper.features.storage.create_storage.presentation.CreateStorageViewModel

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    viewModel: CreateStorageViewModel,
    navHostController: NavHostController,
) {
    TopBarWithLogoComponent(
        modifier = modifier,
        onClick = {
            when (viewModel.createStorageScreenState.value) {
                CreateStorageScreenState.TypingTitle -> {
                    navHostController.popBackStack()
                }

                CreateStorageScreenState.ChooseStorageType -> {
                    viewModel.createStorageScreenState.value = CreateStorageScreenState.TypingTitle
                }

                CreateStorageScreenState.AttachFiles -> {
                    viewModel.createStorageScreenState.value = CreateStorageScreenState.ChooseStorageType
                }
            }
        },
    )
}