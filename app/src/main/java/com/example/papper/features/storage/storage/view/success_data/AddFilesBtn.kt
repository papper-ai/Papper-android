package com.example.papper.features.storage.storage.view.success_data

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.papper.R
import com.example.papper.features.common.components.StrokeButtonComponent
import com.example.papper.features.storage.storage.presentation.StorageViewModel
import com.example.papper.theme.dimens
import com.example.papper.utils.GetFile.getFile
import com.example.papper.utils.getMultipleFilePickerLauncher

@Composable
fun AddFilesBtn(
    modifier: Modifier = Modifier,
    viewModel: StorageViewModel,
) {
    val context = LocalContext.current
    val mimeTypeFilter = arrayOf("application/pdf", "text/plain", "text/markdown", "application/vnd.openxmlformats-officedocument.wordprocessingml.document")
    val multipleFilePicker = getMultipleFilePickerLauncher(
        onResult = { list ->
            for (uri in list) {
                val file = uri.getFile(context)
                if (file != null) {
                    viewModel.addFile(file)
                }
            }
        }
    )

    StrokeButtonComponent(
        modifier = modifier
            .padding(start = MaterialTheme.dimens.gapBetweenComponentScreen, end = 5.dp),
        onClick = {
            multipleFilePicker.launch(mimeTypeFilter)
        },
        text = stringResource(id = R.string.attach_file),
        isLoading = viewModel.btnLoading.value
    )
}