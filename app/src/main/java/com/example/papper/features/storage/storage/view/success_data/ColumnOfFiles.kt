package com.example.papper.features.storage.storage.view.success_data

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.papper.R
import com.example.papper.features.storage.storage.model.FilePresentationModel
import com.example.papper.features.storage.storage.presentation.StorageViewModel
import com.example.papper.features.storage.storage.view.success_data.storage_item.StorageItemBasic
import com.example.papper.theme.Heading2
import com.example.papper.theme.dimens

@Composable
fun ColumnOfFiles(
    modifier: Modifier = Modifier,
    viewModel: StorageViewModel,
    list: List<FilePresentationModel>,
) {
    if (list.isEmpty()) {
        Box(
            modifier = modifier
                .fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ) {
            Text(
                text = stringResource(id = R.string.nothing_there),
                style = MaterialTheme.typography.Heading2,
                color = MaterialTheme.colorScheme.onPrimary,
            )
        }
    } else {
        LazyColumn(
            modifier = modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Top,
            contentPadding = PaddingValues(bottom = MaterialTheme.dimens.bottomGap),
        ) {
            items(
                items = list,
            ) { file ->
                StorageItemBasic(
                    viewModel = viewModel,
                    file = file
                )
                Spacer(modifier = Modifier.padding(bottom = MaterialTheme.dimens.bottomGap))
            }
        }
    }
}