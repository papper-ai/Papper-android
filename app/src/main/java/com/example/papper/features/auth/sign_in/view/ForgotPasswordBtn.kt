package com.example.papper.features.auth.sign_in.view

import androidx.compose.foundation.clickable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.papper.R
import com.example.papper.theme.Heading2

@Composable
fun ForgotPasswordBtn(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Text(
        modifier = modifier
            .clickable(
                onClick = onClick,
            ),
        text = stringResource(id = R.string.forgot_password),
        style = MaterialTheme.typography.Heading2,
        color = MaterialTheme.colorScheme.onSecondary,
    )
}