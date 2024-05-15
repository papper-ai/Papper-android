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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.papper.R
import com.example.papper.features.common.components.AlertDialogComponent
import com.example.papper.features.common.components.TextInputAlertDialog
import com.example.papper.features.common.components.TopBarWithTitleSettingsComponent
import com.example.papper.features.storage.storage.presentation.StorageViewModel
import com.example.papper.theme.Buttons

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    viewModel: StorageViewModel,
    navHostController: NavHostController,
    title: String
) {
    var isExpanded by remember { mutableStateOf(false) }
    val openRenameDialog = remember { mutableStateOf(false) }
    val openDeleteDialog = remember { mutableStateOf(false) }

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
                    text = stringResource(id = R.string.rename_storage),
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
                    text = stringResource(id = R.string.delete_storage),
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
            viewModel.renameStorage(newTitle)
        },
        onDismiss = {openRenameDialog.value = false},
        showDialog = openRenameDialog.value,
        title = title,
        dialogTitle = stringResource(id = R.string.rename_chat),
        confirmText = stringResource(id = R.string.rename),
        cancelText = stringResource(id = R.string.cancel),
    )

    AlertDialogComponent(
        onConfirm = {
            viewModel.deleteStorage()
        },
        onDismiss = {
            openDeleteDialog.value = false
        },
        showDialog = openDeleteDialog.value,
        title = stringResource(id = R.string.delete_storage),
        text = "${stringResource(id = R.string.delete_storage)} $title? Также удалятся все связанные с ним чаты.",
        confirmText = stringResource(id = R.string.delete),
        cancelText = stringResource(id = R.string.cancel),
    )

}