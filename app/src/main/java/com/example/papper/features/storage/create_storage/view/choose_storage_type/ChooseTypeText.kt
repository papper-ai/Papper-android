package com.example.papper.features.storage.create_storage.view.choose_storage_type

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.papper.R
import com.example.papper.theme.Heading2

@Composable
fun ChooseTypeText(
    modifier: Modifier = Modifier,
) {
    Text(
        text = stringResource(id = R.string.choose_storage_type),
        style = MaterialTheme.typography.Heading2,
        color = MaterialTheme.colorScheme.onPrimary
    )
}