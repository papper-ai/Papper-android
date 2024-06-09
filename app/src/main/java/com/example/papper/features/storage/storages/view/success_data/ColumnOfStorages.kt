package com.example.papper.features.storage.storages.view.success_data

import androidx.compose.foundation.clickable
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
import androidx.navigation.NavHostController
import com.example.papper.R
import com.example.papper.features.storage.storages.model.PresentationStoragePreviewModel
import com.example.papper.features.storage.storages.presentation.StoragesViewModel
import com.example.papper.features.storage.storages.view.success_data.storage_item.ItemBasic
import com.example.papper.theme.Heading2
import com.example.papper.theme.dimens

@Composable
fun ColumnOfStorages(
    modifier: Modifier = Modifier,
    viewModel: StoragesViewModel,
    navHostController: NavHostController,
    list: List<PresentationStoragePreviewModel>,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(bottom = MaterialTheme.dimens.bottomGap)
    ) {
        if (list.isEmpty()) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = MaterialTheme.dimens.bottomGap2),
                    contentAlignment = Alignment.TopCenter
                ) {
                    Text(
                        modifier = Modifier
                            .clickable {
                                viewModel.navigateToCreateStorageScreen()
                            },
                        text = stringResource(id = R.string.nothing_there),
                        style = MaterialTheme.typography.Heading2,
                        color = MaterialTheme.colorScheme.onPrimary,
                    )
                }
            }
        } else {
            items(
                items = list,
            ) { storage ->
                ItemBasic(
                    viewModel = viewModel,
                    navHostController = navHostController,
                    storage = storage
                )
                Spacer(modifier = Modifier.padding(bottom = MaterialTheme.dimens.bottomGap))
            }
        }
    }
}