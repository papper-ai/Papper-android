package com.example.papper.features.storage.create_file.view.attach_photos

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.papper.R
import com.example.papper.features.common.components.ButtonComponent
import com.example.papper.features.storage.create_file.presentation.CreateFileViewModel
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun ConvertPhotosBtn(
    modifier: Modifier = Modifier,
    viewModel: CreateFileViewModel,
) {
    ButtonComponent(
        modifier = modifier,
        onClick = {

        },
        text = stringResource(id = R.string.convert),
        isLoading = viewModel.createStorageBtnStatus.value.isLoading,
        isEnable = ((viewModel.createStorageBtnStatus.value.isEnable) && viewModel.collectAsState().value.listOfPhotos.isNotEmpty()),
    )
}

data class CreateStorageBtnStatus(
    val isLoading: Boolean,
    val isEnable: Boolean,
)