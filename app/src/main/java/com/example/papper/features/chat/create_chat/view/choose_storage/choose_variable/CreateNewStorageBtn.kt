package com.example.papper.features.chat.create_chat.view.choose_storage.choose_variable

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.example.papper.R
import com.example.papper.features.common.components.SmallButtonComponent
import com.example.papper.navigation.Screens

@Composable
fun CreateNewStorageBtn(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
) {
    SmallButtonComponent(
        modifier = modifier,
        onClick = {
            navHostController.navigate(Screens.CreateStorageScreen.route)
        },
        text = stringResource(id = R.string.create_new),
    )
}
