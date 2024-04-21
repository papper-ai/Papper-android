package com.example.papper.features.storage.storages.view.error_data

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.papper.R
import com.example.papper.features.common.components.ErrorMessageComponent

@Composable
fun ErrorMsg(
    modifier: Modifier = Modifier,
) {
   ErrorMessageComponent(
       errorText = stringResource(id = R.string.loading_storages_error)
   )
}