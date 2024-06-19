package com.example.papper.features.auth.sign_in_by_face.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.papper.features.auth.sign_in_by_face.presentation.SignInByFaceViewModel
import com.example.papper.features.common.components.TopBarWithLogoComponent

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    viewModel: SignInByFaceViewModel,
) {
    TopBarWithLogoComponent(
        modifier = modifier,
        onClick = {

        }
    )
}