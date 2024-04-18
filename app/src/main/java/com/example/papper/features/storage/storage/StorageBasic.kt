package com.example.papper.features.storage.storage

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.papper.features.storage.storage.presentation.StorageScreenState
import com.example.papper.features.storage.storage.presentation.StorageViewModel
import com.example.papper.features.storage.storage.view.error_data.ErrorBasic
import com.example.papper.features.storage.storage.view.loading_data.LoadingBasic
import com.example.papper.features.storage.storage.view.success_data.SuccessBasic

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun StorageBasic(
    modifier: Modifier = Modifier,
    viewModel: StorageViewModel,
    navHostController: NavHostController,
) {
    when (viewModel.storageScreenState.value) {
        is StorageScreenState.Loading -> LoadingBasic(
            modifier = modifier,
            navHostController = navHostController,
        )

        is StorageScreenState.Success -> SuccessBasic(
            modifier = modifier,
            viewModel = viewModel,
            navHostController = navHostController,
        )

        is StorageScreenState.Error -> ErrorBasic(
            modifier = modifier,
            viewModel = viewModel,
            navHostController = navHostController,
        )
    }
}