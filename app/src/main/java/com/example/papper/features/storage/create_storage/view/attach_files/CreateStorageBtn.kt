package com.example.papper.features.storage.create_storage.view.attach_files

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.example.papper.R
import com.example.papper.features.common.components.ButtonComponent
import com.example.papper.features.storage.create_storage.presentation.CreateStorageViewModel

@Composable
fun CreateStorageBtn(
    modifier: Modifier = Modifier,
    viewModel: CreateStorageViewModel,
    navHostController: NavHostController,
) {
    ButtonComponent(
        modifier = modifier,
        onClick = {
            navHostController.previousBackStackEntry?.destination?.route?.let { route ->
                viewModel.createStorage(
                    route
                )
            }
        },
        text = stringResource(id = R.string.create),
        isLoading = viewModel.createStorageBtnStatus.value.isLoading,
        isEnable = viewModel.createStorageBtnStatus.value.isEnable,
    )
}

data class CreateStorageBtnStatus(
    val isLoading: Boolean,
    val isEnable: Boolean,
)