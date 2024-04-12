package com.example.papper.features.storage.storages.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.papper.features.common.components.TopBarWithLogoComponent
import com.example.papper.features.storage.storages.presentation.StoragesViewModel

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    viewModel: StoragesViewModel,
    navHostController: NavHostController,
) {
    TopBarWithLogoComponent(
        modifier = modifier,
        onClick = {
            navHostController.popBackStack()
        },
    )
}