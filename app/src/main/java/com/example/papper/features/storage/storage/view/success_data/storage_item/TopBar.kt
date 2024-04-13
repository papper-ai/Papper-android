package com.example.papper.features.storage.storage.view.success_data.storage_item

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
        modifier = modifier,
        onBackBtnClick = {
            navHostController.popBackStack()
        },
        onSettingsBtnClick = {
            //todo сделать всплывающее окно
        },
        text = title,
    )
}