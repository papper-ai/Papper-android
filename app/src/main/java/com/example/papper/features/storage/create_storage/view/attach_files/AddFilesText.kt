package com.example.papper.features.storage.create_storage.view.attach_files

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.papper.R
import com.example.papper.theme.Heading2

@Composable
fun AddFilesText(
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier,
        text = stringResource(id = R.string.add_files_into_storage),
        style = MaterialTheme.typography.Heading2,
        color = MaterialTheme.colorScheme.onPrimary,
    )
}