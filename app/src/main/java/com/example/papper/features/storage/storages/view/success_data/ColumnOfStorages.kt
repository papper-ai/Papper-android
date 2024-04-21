package com.example.papper.features.storage.storages.view.success_data

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.papper.features.storage.storages.model.PresentationStoragePreviewModel
import com.example.papper.features.storage.storages.presentation.StoragesViewModel
import com.example.papper.features.storage.storages.view.success_data.storage_item.ItemBasic
import com.example.papper.theme.dimens

@Composable
fun ColumnOfStorages(
    modifier: Modifier = Modifier,
    viewModel: StoragesViewModel,
    navHostController: NavHostController,
    listOfChats: List<PresentationStoragePreviewModel>,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxHeight(),
        contentPadding = PaddingValues(bottom = MaterialTheme.dimens.bottomGap)
    ) {
        items(
            items = listOfChats,
        ) { storage ->
            ItemBasic(viewModel = viewModel, navHostController = navHostController, storage = storage)
            Spacer(modifier = Modifier.padding(bottom = MaterialTheme.dimens.bottomGap))
        }
    }
}