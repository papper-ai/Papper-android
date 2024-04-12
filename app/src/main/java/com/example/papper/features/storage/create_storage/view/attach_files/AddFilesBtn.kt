package com.example.papper.features.storage.create_storage.view.attach_files

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.papper.R
import com.example.papper.features.common.components.StrokeButtonComponent

@Composable
fun AddFilesBtn(
    modifier: Modifier = Modifier,
) {
    StrokeButtonComponent(
        modifier = modifier,
        onClick = {

        },
        text = stringResource(id = R.string.attach_file),
    )
}