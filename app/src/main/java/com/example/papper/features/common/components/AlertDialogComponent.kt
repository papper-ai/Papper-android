package com.example.papper.features.common.components

import android.graphics.Bitmap
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.papper.R
import com.example.papper.theme.dimens

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

@Composable
fun ConfirmAlertDialog(
    onDismiss: () -> Unit,
    onConfirm: (String) -> Unit,
    //название для чата или хранилища
    title: String,
    text: String,
    showDialog: Boolean,
    dialogTitle: String,
    confirmText: String,
    cancelText: String,
    placeHolderText: String,
) {
    if (showDialog) {
        val titleState = remember { mutableStateOf("") }

        AlertDialog(
            onDismissRequest = onDismiss,
            confirmButton = {
                Button(
                    onClick = {
                        onConfirm(titleState.value)
                        onDismiss()
                    },
                    enabled = (titleState.value == title),
                    colors = ButtonDefaults.buttonColors (
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary,
                        disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                        disabledContentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                    ),
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
                    Text(text = text)
                    Spacer(modifier = Modifier.height(MaterialTheme.dimens.bottomGap2))
                    OutlinedTextFieldComponent(
                        value = titleState.value,
                        onValueChange = { newTitle ->
                            titleState.value = newTitle
                        },
                        placeholder = placeHolderText,
                        singleLine = true,
                    )
                }
            },
            containerColor = MaterialTheme.colorScheme.onBackground,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            textContentColor = MaterialTheme.colorScheme.onPrimary,
        )
    }
}

@Composable
fun ImageAlertDialog(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
    isWithReplaceImage: Boolean = false,
    onReplaceClick: () -> Unit = {if (isWithReplaceImage) {throw Exception ("ReplaceImageAlertDialog: onReplaceClick is not defined")} },
    onGetPhotoClick: () -> Unit = {if (isWithReplaceImage) {throw Exception ("ReplaceImageAlertDialog: onGetPhotoClick is not defined")} },
    image: Bitmap,
) {
    Dialog(
        onDismissRequest = { onDismissRequest() },
        properties = DialogProperties(
            dismissOnClickOutside = true,
            dismissOnBackPress = true,
        ),
    ) {
        BackHandler {
            onDismissRequest()
        }

        Column(
            modifier = Modifier
                .wrapContentSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                modifier = Modifier
                    .wrapContentSize()
                    .clip(shape = RoundedCornerShape(MaterialTheme.dimens.buttonCornerRadius)),
                bitmap = image.asImageBitmap(),
                contentDescription = "image",
                contentScale = ContentScale.FillBounds
            )
            if (isWithReplaceImage) {
                Spacer(modifier = Modifier.height(MaterialTheme.dimens.buttonGap))
                Row {
                    StrokeButtonComponent(
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = MaterialTheme.dimens.buttonGap/2),
                        onClick = {
                            onReplaceClick()
                        },
                        text = stringResource(id = R.string.replace_photo),
                        isWithBackground = true,
                    )
                    SmallStrokeButtonComponent(
                        modifier = Modifier
                            .weight(0.25f)
                            .padding(start = MaterialTheme.dimens.buttonGap/2),
                        iconDrawable = R.drawable.camera_24,
                        onClick = {
                            onGetPhotoClick()
                        },
                        isWithBackground = true,
                    )
                }
            }
        }
    }
}