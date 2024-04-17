package com.example.papper.features.chat.chat.view.loading_data

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.papper.features.common.components.TopBarWithLogoComponent

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
) {
    TopBarWithLogoComponent(
        modifier = modifier,
        onClick = {
            navHostController.popBackStack()
        }
    )
}