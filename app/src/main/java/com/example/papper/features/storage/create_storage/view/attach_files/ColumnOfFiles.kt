package com.example.papper.features.storage.create_storage.view.attach_files

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.papper.features.storage.create_storage.presentation.CreateStorageViewModel
import com.example.papper.features.storage.create_storage.view.attach_files.storage_item.StorageItemBasic
import com.example.papper.theme.dimens
import java.io.File

@Composable
fun ColumnOfFiles(
    modifier: Modifier = Modifier,
    viewModel: CreateStorageViewModel,
    list: List<File>,
) {
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