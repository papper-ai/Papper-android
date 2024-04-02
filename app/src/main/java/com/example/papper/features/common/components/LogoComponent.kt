package com.example.papper.features.common.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.papper.R
import com.example.papper.theme.Logo

@Composable
fun SmallLogoComponent(modifier: Modifier = Modifier) {
    Text(
        modifier = modifier,
        text = stringResource(id = R.string.app_name),
        style = MaterialTheme.typography.Logo,
        fontSize = 40.sp,
        color = MaterialTheme.colorScheme.onPrimary,
        textAlign = TextAlign.Center,
        maxLines = 1,
    )
}

@Composable
fun BigLogoComponent(modifier: Modifier = Modifier) {
    Text(
        modifier = modifier,
        text = stringResource(id = R.string.app_name),
        style = MaterialTheme.typography.Logo,
        color = MaterialTheme.colorScheme.onPrimary,
        textAlign = TextAlign.Center,
        maxLines = 1,
    )
}