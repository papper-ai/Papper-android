package com.example.papper.features.storage.create_storage.view.choose_storage_type

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.papper.R
import com.example.papper.features.common.components.RadioBtnComponent
import com.example.papper.features.storage.create_storage.presentation.CreateStorageViewModel
import com.example.papper.features.storage.create_storage.presentation.StorageType

@Composable
fun VectorBtn(
    modifier: Modifier = Modifier,
    viewModel: CreateStorageViewModel,
) {
    RadioBtnComponent(
        modifier = modifier,
        onClick = {
            viewModel.vectorBtnClick()
        },
        text = stringResource(id = R.string.vector_type),
        isSelected = viewModel.chooseStorageType.value == StorageType.vector ,
    )
}