package com.example.papper.features.storage.create_file.view.attach_photos

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.papper.R
import com.example.papper.features.common.components.StrokeButtonComponent
import com.example.papper.features.storage.create_file.presentation.CreateFileViewModel
import com.example.papper.theme.dimens
import com.example.papper.utils.getMultipleFilePickerLauncher

@Composable
fun AddPhotosBtn(
    modifier: Modifier = Modifier,
    viewModel: CreateFileViewModel,
) {
    val multipleFilePicker = getMultipleFilePickerLauncher(
        onResult = { list ->
            for (uri in list) {
                viewModel.addPhoto(image = uri)
            }
        }
    )

    StrokeButtonComponent(
        modifier = modifier
            .padding(start = MaterialTheme.dimens.gapBetweenComponentScreen, end = MaterialTheme.dimens.buttonGap/2),
        onClick = {
            multipleFilePicker.launch(arrayOf("image/*"))
        },
        text = stringResource(id = R.string.attach_photo),
    )
}