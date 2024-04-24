package com.example.papper.features.storage.create_storage.view.attach_files

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.papper.R
import com.example.papper.theme.Buttons

@Composable
fun EmptyColumnText(
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier,
        text = stringResource(id = R.string.nothing_there),
        style = MaterialTheme.typography.Buttons,
        color = MaterialTheme.colorScheme.onPrimary,
    )
}