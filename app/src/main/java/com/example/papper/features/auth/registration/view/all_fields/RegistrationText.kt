package com.example.papper.features.auth.registration.view.all_fields

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.papper.R
import com.example.papper.theme.Heading1
import com.example.papper.theme.dimens

@Composable
fun RegistrationText(
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier
            .padding(MaterialTheme.dimens.gapBetweenComponentScreen),
        text = stringResource(id = R.string.registration),
        style = MaterialTheme.typography.Heading1,
        color = MaterialTheme.colorScheme.onPrimary,
    )
}