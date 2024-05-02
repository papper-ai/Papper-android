package com.example.papper.features.chat.chat.view.success_data

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
import com.example.papper.features.chat.chat.presentation.ChatViewModel
import com.example.papper.features.common.components.TopBarWithTitleSettingsComponent
import com.example.papper.theme.Buttons

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    viewModel: ChatViewModel,
    navHostController: NavHostController,
    title: String
) {
    var isExpanded by remember { mutableStateOf(false) }

    TopBarWithTitleSettingsComponent(
        onBackBtnClick = { navHostController.popBackStack() },
        onSettingsBtnClick = {
            isExpanded = true
        },
        text = title
    )

    DropdownMenu(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.onBackground),
        offset = DpOffset(300.dp,250.dp),
        expanded = isExpanded,
        onDismissRequest = { isExpanded = false }
    ) {
        DropdownMenuItem(
            text = {
                Text(
                    text = "Переменовать чат",
                    style = MaterialTheme.typography.Buttons,
                    color = MaterialTheme.colorScheme.onPrimary,
                )
            },
            onClick = { /*TODO*/ }
        )
        DropdownMenuItem(
            text = {
                Text(
                    text = "Перейти в хранилище",
                    style = MaterialTheme.typography.Buttons,
                    color = MaterialTheme.colorScheme.onPrimary,
                )
            },
            onClick = { /*TODO*/ }
        )
        DropdownMenuItem(
            text = {
                Text(
                    text = "Очистить историю чата",
                    style = MaterialTheme.typography.Buttons,
                    color = MaterialTheme.colorScheme.onPrimary,
                )
            },
            onClick = { /*TODO*/ }
        )
        DropdownMenuItem(
            text = {
                Text(
                    text = "Удалить чат",
                    style = MaterialTheme.typography.Buttons,
                    color = MaterialTheme.colorScheme.onPrimary,
                )
            },
            onClick = { /*TODO*/ }
        )
    }
}