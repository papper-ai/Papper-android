package com.example.papper.features.storage.storage.view.success_data

import androidx.compose.foundation.background
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.papper.features.common.components.TopBarWithTitleSettingsComponent
import com.example.papper.theme.Buttons

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    title: String
) {
    var isExpanded by remember { mutableStateOf(false) }

    TopBarWithTitleSettingsComponent(
        modifier = modifier,
        onBackBtnClick = {
            navHostController.popBackStack()
        },
        onSettingsBtnClick = {
            isExpanded = true
        },
        text = title,
    )

    DropdownMenu(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.onBackground),
        offset = DpOffset(300.dp, 160.dp),
        expanded = isExpanded,
        onDismissRequest = { isExpanded = false }
    ) {
        DropdownMenuItem(
            text = {
                Text(
                    text = "Переменовать хранилище",
                    style = MaterialTheme.typography.Buttons,
                    color = MaterialTheme.colorScheme.onPrimary,
                )
            },
            onClick = { /*TODO*/ }
        )
        DropdownMenuItem(
            text = {
                Text(
                    text = "Удалить хранилище",
                    style = MaterialTheme.typography.Buttons,
                    color = MaterialTheme.colorScheme.onPrimary,
                )
            },
            onClick = { /*TODO*/ }
        )
    }
}