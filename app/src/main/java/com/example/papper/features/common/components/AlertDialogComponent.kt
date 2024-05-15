package com.example.papper.features.common.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

@Composable
fun AlertDialogComponent(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
    showDialog: Boolean,
    title: String,
    text: String,
    confirmText: String,
    cancelText: String,
) {
    if (showDialog) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = {
                Text(text = title)
            },
            text = {
                Text(text = text)
            },
            confirmButton = {
                Button(
                    onClick = {
                        onConfirm()
                        onDismiss()
                    }
                ) {
                    Text(text = confirmText)
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        onDismiss()
                    }
                ) {
                    Text(text = cancelText)
                }
            },
            containerColor = MaterialTheme.colorScheme.onBackground,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            textContentColor = MaterialTheme.colorScheme.onPrimary,
        )
    }
}

@Composable
fun TextInputAlertDialog(
    onDismiss: () -> Unit,
    onConfirm: (String) -> Unit,
    title: String,
    showDialog: Boolean,
    dialogTitle: String,
    confirmText: String,
    cancelText: String,
) {
    if (showDialog) {
        val titleState = remember { mutableStateOf(title) }

        AlertDialog(
            onDismissRequest = onDismiss,
            confirmButton = {
                Button(
                    onClick = {
                        onConfirm(titleState.value)
                        onDismiss()
                    },
                    enabled = titleState.value.isNotEmpty()
                ) {
                    Text(confirmText)
                }
            },
            dismissButton = {
                Button(
                    onClick = onDismiss
                ) {
                    Text(cancelText)
                }
            },
            title = { Text(text = dialogTitle) },
            text = {
                Column {
                    OutlinedTextFieldComponent(
                        value = titleState.value,
                        onValueChange = { newTitle ->
                            titleState.value = newTitle
                        },
                        placeholder = "Название",
                        singleLine = true,
                    )
//                    TextField(
//                        value = titleState.value,
//                        onValueChange = { newTitle ->
//                            titleState.value = newTitle
//                        }
//                    )
                }
            },
            containerColor = MaterialTheme.colorScheme.onBackground,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            textContentColor = MaterialTheme.colorScheme.onPrimary,
        )
    }
}