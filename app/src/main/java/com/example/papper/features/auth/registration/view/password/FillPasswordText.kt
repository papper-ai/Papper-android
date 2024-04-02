package com.example.papper.features.auth.registration.view.password

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.papper.R
import com.example.papper.theme.Heading2

@Composable
fun FillPasswordText() {
    Text(
        text = stringResource(id = R.string.fill_your_password),
        style = MaterialTheme.typography.Heading2,
        color = MaterialTheme.colorScheme.onPrimary
    )
}