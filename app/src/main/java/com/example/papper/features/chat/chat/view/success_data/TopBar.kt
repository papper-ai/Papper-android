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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.papper.R
import com.example.papper.features.chat.chat.presentation.ChatViewModel
import com.example.papper.features.common.components.AlertDialogComponent
import com.example.papper.features.common.components.ConfirmAlertDialog
import com.example.papper.features.common.components.TextInputAlertDialog
import com.example.papper.features.common.components.TopBarWithTitleSettingsComponent
import com.example.papper.theme.Buttons

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    viewModel: ChatViewModel,
    navHostController: NavHostController,
    title: String,
    archiveStatus: Boolean,
) {
    var isExpanded by remember { mutableStateOf(false) }
    val openRenameDialog = remember { mutableStateOf(false) }
    val openDeleteDialog = remember { mutableStateOf(false) }

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
        offset = DpOffset(300.dp,160.dp),
        expanded = isExpanded,
        onDismissRequest = { isExpanded = false }
    ) {
        DropdownMenuItem(
            text = {
                Text(
                    text = stringResource(id = R.string.rename_chat),
                    style = MaterialTheme.typography.Buttons,
                    color = MaterialTheme.colorScheme.onPrimary,
                )
            },
            onClick = {
                isExpanded = false
                openRenameDialog.value = true
            }
        )
        DropdownMenuItem(
            text = {
                Text(
                    text = stringResource(id = R.string.go_to_storage),
                    style = MaterialTheme.typography.Buttons,
                    color = MaterialTheme.colorScheme.onPrimary,
                )
            },
            onClick = {
                isExpanded = false
                viewModel.navigateToStorageScreen()
            }
        )
        DropdownMenuItem(
            text = {
                Text(
                    text = if (archiveStatus) stringResource(id = R.string.unarchive_chat) else stringResource(id = R.string.archive_chat),
                    style = MaterialTheme.typography.Buttons,
                    color = MaterialTheme.colorScheme.onPrimary,
                )
            },
            onClick = {
                isExpanded = false
                viewModel.changeArchiveStatus()
            }
        )
        DropdownMenuItem(
            text = {
                Text(
                    text = stringResource(id = R.string.clear_history_of_chat),
                    style = MaterialTheme.typography.Buttons,
                    color = MaterialTheme.colorScheme.onPrimary,
                )
            },
            onClick = {
                isExpanded = false
                viewModel.clearChatHistory()
            }
        )
        DropdownMenuItem(
            text = {
                Text(
                    text = stringResource(id = R.string.delete_chat),
                    style = MaterialTheme.typography.Buttons,
                    color = MaterialTheme.colorScheme.onPrimary,
                )
            },
            onClick = {
                isExpanded = false
                openDeleteDialog.value = true
            }
        )
    }

    TextInputAlertDialog(
        onConfirm = { newTitle ->
            viewModel.renameChat(newTitle)
        },
        onDismiss = {openRenameDialog.value = false},
        showDialog = openRenameDialog.value,
        title = title,
        dialogTitle = stringResource(id = R.string.rename_chat),
        confirmText = stringResource(id = R.string.rename),
        cancelText = stringResource(id = R.string.cancel),
    )

    ConfirmAlertDialog(
        onConfirm = {
            viewModel.deleteChat()
        },
        onDismiss = {
            openDeleteDialog.value = false
        },
        showDialog = openDeleteDialog.value,
        title = title,
        text = "${stringResource(id = R.string.delete_chat)} $title?",
        dialogTitle = stringResource(id = R.string.delete_chat),
        confirmText = stringResource(id = R.string.delete),
        cancelText = stringResource(id = R.string.cancel),
        placeHolderText = stringResource(id = R.string.chat_title),
    )

}