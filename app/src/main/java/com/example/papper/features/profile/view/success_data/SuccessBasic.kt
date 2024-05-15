package com.example.papper.features.profile.view.success_data

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.papper.features.profile.presentation.ProfileViewModel
import com.example.papper.theme.Heading1
import com.example.papper.theme.dimens
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun SuccessBasic(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel,
) {
    Surface(
        modifier = modifier
            .fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
    ) {
        Text(
            modifier = Modifier.padding(start = MaterialTheme.dimens.gapBetweenComponentScreen, top = MaterialTheme.dimens.gapBetweenComponentScreen),
            text = "Логин: ${viewModel.collectAsState().value.login}",
            style = MaterialTheme.typography.Heading1,
            color = MaterialTheme.colorScheme.onPrimary
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = MaterialTheme.dimens.bottomGap3),
            verticalArrangement = Arrangement.Bottom
        ) {
            LogOutBtn(viewModel = viewModel)
        }
    }
}