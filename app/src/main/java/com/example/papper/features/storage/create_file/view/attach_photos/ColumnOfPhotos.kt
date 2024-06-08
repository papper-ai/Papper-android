package com.example.papper.features.storage.create_file.view.attach_photos

import android.graphics.Bitmap
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
import com.example.papper.features.storage.create_file.presentation.CreateFileViewModel
import com.example.papper.theme.dimens

@Composable
fun ColumnOfPhotos(
    modifier: Modifier = Modifier,
    viewModel: CreateFileViewModel,
    list: List<Bitmap>,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Top,
        contentPadding = PaddingValues(bottom = MaterialTheme.dimens.bottomGap),
    ) {
        items(
            items = list,
        ) { photo ->
//            PhotoItemBasic(
//                viewModel = viewModel,
//                file = file
//            )
            Spacer(modifier = Modifier.padding(bottom = MaterialTheme.dimens.bottomGap))
        }
    }
}