package com.example.papper.features.storage.create_file.view.attach_photos

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.papper.features.storage.create_file.model.AttachPhotoModel
import com.example.papper.features.storage.create_file.presentation.CreateFileViewModel
import com.example.papper.theme.dimens

@Composable
fun ColumnOfPhotos(
    modifier: Modifier = Modifier,
    viewModel: CreateFileViewModel,
    list: List<AttachPhotoModel>,
) {
    LazyVerticalStaggeredGrid(
        modifier = modifier
            .fillMaxSize()
            .padding(
                start = MaterialTheme.dimens.gapBetweenComponentScreen,
                end = MaterialTheme.dimens.gapBetweenComponentScreen,
                bottom = MaterialTheme.dimens.gapBetweenComponents,
            ),
        columns = StaggeredGridCells.Fixed(2),
        verticalItemSpacing = MaterialTheme.dimens.gapBetweenComponentScreen,
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.dimens.gapBetweenComponentScreen),
        content = {
            items(list) { photo ->
                PhotoItemBasic(
                    viewModel = viewModel,
                    photo = photo,
                )
            }
        },
    )
}