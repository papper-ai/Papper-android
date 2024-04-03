package com.example.papper.features.common.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.papper.theme.dimens

@Composable
fun ProgressBarComponent(
    modifier: Modifier = Modifier,
) {
    CircularProgressIndicator(
        modifier = modifier
            .size(MaterialTheme.dimens.progressBarSize),
        color = MaterialTheme.colorScheme.onPrimary,
        strokeWidth = MaterialTheme.dimens.progressBarWeight
    )
}