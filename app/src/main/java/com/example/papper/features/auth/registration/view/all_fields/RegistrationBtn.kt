package com.example.papper.features.auth.registration.view.all_fields

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import com.example.papper.R
import com.example.papper.features.auth.registration.presentation.AllFieldsScreenState
import com.example.papper.features.auth.registration.presentation.RegistrationScreenState
import com.example.papper.features.auth.registration.presentation.RegistrationViewModel
import com.example.papper.features.common.components.ButtonComponent1
import com.example.papper.navigation.Screens
import kotlinx.coroutines.coroutineScope
import okhttp3.internal.wait

@Composable
fun RegistrationBtn(
    modifier: Modifier = Modifier,
    viewModel: RegistrationViewModel,
    navHostController: NavHostController,
) {
    ButtonComponent1(
        modifier = modifier,
        onClick = {
            viewModel.createAccount(navHostController = navHostController)
        },
        text = stringResource(id = R.string.registration),
        isEnable = viewModel.allFieldScreenState.value !is AllFieldsScreenState.Loading,
        isLoading = viewModel.allFieldScreenState.value is AllFieldsScreenState.Loading,
    )
}