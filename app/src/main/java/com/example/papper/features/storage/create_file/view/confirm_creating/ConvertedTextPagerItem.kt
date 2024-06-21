package com.example.papper.features.storage.create_file.view.confirm_creating

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.papper.R
import com.example.papper.features.common.components.BigTextInputAlertDialog
import com.example.papper.features.common.components.TextInputAlertDialog
import com.example.papper.features.common.components.drawVerticalScrollbar
import com.example.papper.features.storage.create_file.model.PhotoModel
import com.example.papper.features.storage.create_file.presentation.CreateFileViewModel
import com.example.papper.features.storage.create_file.view.PhotoItemBasic
import com.example.papper.theme.TypingText
import com.example.papper.theme.dimens

@Composable
fun ConvertedTextPagerItem(
    modifier: Modifier = Modifier,
    viewModel: CreateFileViewModel,
    convertedPhoto: PhotoModel,
) {
    val scroll = rememberScrollState(0)
    var textFieldAlertDialogVisibility by remember {
        mutableStateOf(false)
    }
    var confirmDelete by remember {
        mutableStateOf(false)
    }

    Box(
        modifier = modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier
                .wrapContentWidth()
                .padding(top = MaterialTheme.dimens.gapBetweenComponents),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            PhotoItemBasic(
                onDeleteClick = { confirmDelete = true },
                viewModel = viewModel,
                photo = PhotoModel(
                    id = convertedPhoto.id,
                    imageUri = convertedPhoto.imageUri,
                    text = convertedPhoto.text
                ),
            )
            Spacer(modifier = Modifier.height(MaterialTheme.dimens.buttonGap))
            Box(
                modifier = Modifier
                    .fillMaxHeight(0.8f)
                    .fillMaxWidth()
                    .padding(
                        horizontal = MaterialTheme.dimens.gapBetweenComponentScreen,
                        vertical = MaterialTheme.dimens.gapBetweenComponentScreen
                    )
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.onPrimary,
                        shape = RoundedCornerShape(MaterialTheme.dimens.buttonGap)
                    )
                    .clickable {
                        textFieldAlertDialogVisibility = true
                    }
                    .padding(
                        horizontal = MaterialTheme.dimens.gapBetweenComponents,
                        vertical = MaterialTheme.dimens.gapBetweenComponents
                    )
                    .drawVerticalScrollbar(scroll)
                    .verticalScroll(scroll)
            ) {
                Text(
                    text = if (convertedPhoto.text.isNotEmpty()) convertedPhoto.text else "Пустой текст",
                    modifier = Modifier,
                    style = MaterialTheme.typography.TypingText,
                    color = if (convertedPhoto.text.isNotEmpty()) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSecondary
                )
            }
        }
    }

    BigTextInputAlertDialog(
        onDismiss = { textFieldAlertDialogVisibility = false },
        onConfirm = { viewModel.editConvertedText(photo = convertedPhoto, text = it) },
        textInTextField = convertedPhoto.text,
        placeHolder = stringResource(id = R.string.converted_text),
        showDialog = textFieldAlertDialogVisibility,
        dialogTitle = stringResource(id = R.string.edit_text),
        confirmText = stringResource(id = R.string.confirm),
        cancelText = stringResource(id = R.string.cancel),
        singleLine = false,
    )

    ConfirmDeleteAlertDialog(
        onConfirm = {
            viewModel.deletePhoto(photo = convertedPhoto)
        },
        onDismiss = { confirmDelete = false },
        text = "${stringResource(id = R.string.confirm_delete)} ${convertedPhoto.id}?",
        showDialog = confirmDelete
    )
    
}