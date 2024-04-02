package com.example.papper.features.auth.start

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@Composable
fun StartScreen(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
) {
    StartBasic(
        modifier = modifier,
        navHostController = navHostController,
    )
}

