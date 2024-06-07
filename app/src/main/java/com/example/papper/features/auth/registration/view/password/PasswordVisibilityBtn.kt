package com.example.papper.features.auth.registration.view.password

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.papper.R
import com.example.papper.features.auth.registration.presentation.RegistrationViewModel

@Composable
fun PasswordVisibilityBtn(
    modifier: Modifier = Modifier,
    viewModel: RegistrationViewModel,
) {
    var passwordVisible = viewModel.passwordVisibility

    IconButton(
        modifier = modifier,
        onClick = { viewModel.passwordVisibility.value = !viewModel.passwordVisibility.value },
    ) {
        Icon(
            painter = if (passwordVisible.value) painterResource(id = R.drawable.baseline_visibility_off_24_icon) else painterResource(id = R.drawable.round_visibility_24_icon),
            contentDescription = if (passwordVisible.value) "Hide password" else "Show password",
            tint = MaterialTheme.colorScheme.onPrimaryContainer,
        )
    }
}