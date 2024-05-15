package com.example.papper.features.profile.view.error_data

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.papper.R
import com.example.papper.features.common.components.ButtonComponent
import com.example.papper.features.profile.presentation.ProfileViewModel

@Composable
fun ReloadBtn(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel,
) {
    ButtonComponent(
        modifier = modifier,
        onClick = { viewModel.getLogin() },
        text = stringResource(id = R.string.retry_response)
    )
}