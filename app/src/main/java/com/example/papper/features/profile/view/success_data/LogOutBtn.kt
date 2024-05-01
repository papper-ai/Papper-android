package com.example.papper.features.profile.view.success_data

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.papper.R
import com.example.papper.features.common.components.LogOutButtonComponent
import com.example.papper.features.profile.presentation.ProfileViewModel

@Composable
fun LogOutBtn(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel,
) {
    LogOutButtonComponent(
        onClick = { viewModel.logOut() },
        text = stringResource(id = R.string.logout_from_account)
    )
}