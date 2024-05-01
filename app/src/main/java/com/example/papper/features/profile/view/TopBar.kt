package com.example.papper.features.profile.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.papper.features.common.components.TopBarWithLogoComponent
import com.example.papper.features.profile.presentation.ProfileViewModel


@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel,
) {
    TopBarWithLogoComponent(
        onClick = {
            viewModel.navigateToChatsScreen()
        },
    )
}