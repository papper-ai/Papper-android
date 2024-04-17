package com.example.papper.features.chat.chat.view.success_data

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.papper.features.common.components.TopBarWithTitleSettingsComponent

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    title: String
) {
    TopBarWithTitleSettingsComponent(
        onBackBtnClick = { navHostController.popBackStack() },
        onSettingsBtnClick = { /*TODO добавить меню*/ },
        text = title
    )
}