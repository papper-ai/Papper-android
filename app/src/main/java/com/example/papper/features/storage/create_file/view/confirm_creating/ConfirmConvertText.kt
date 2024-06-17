package com.example.papper.features.storage.create_file.view.confirm_creating

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.papper.R
import com.example.papper.theme.Heading2

@Composable
fun ConfirmConvertText(
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier,
        text = stringResource(id = R.string.confirm_correct_converted),
        style = MaterialTheme.typography.Heading2,
        color = MaterialTheme.colorScheme.onPrimary,
    )
}