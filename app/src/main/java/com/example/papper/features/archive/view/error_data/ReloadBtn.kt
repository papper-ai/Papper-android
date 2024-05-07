package com.example.papper.features.archive.view.error_data

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.papper.R
import com.example.papper.features.archive.presentation.ArchivesViewModel
import com.example.papper.features.common.components.ButtonComponent

@Composable
fun ReloadBtn(
    modifier: Modifier = Modifier,
    viewModel: ArchivesViewModel,
) {
    ButtonComponent(
        modifier = modifier,
        onClick = {
            viewModel.loadData()
        },
        text = stringResource(id = R.string.retry_response)
    )
}