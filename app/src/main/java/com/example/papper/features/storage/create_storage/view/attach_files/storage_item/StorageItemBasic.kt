package com.example.papper.features.storage.create_storage.view.attach_files.storage_item

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import com.example.papper.features.storage.create_storage.presentation.CreateStorageViewModel
import com.example.papper.theme.Heading2
import com.example.papper.theme.dimens
import java.io.File

@Composable
fun StorageItemBasic(
    modifier: Modifier = Modifier,
    viewModel: CreateStorageViewModel,
    file: File,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(MaterialTheme.dimens.buttonsHeight)
            .padding(
                start = MaterialTheme.dimens.gapBetweenComponentScreen * 2,
                end = MaterialTheme.dimens.gapBetweenComponentScreen * 2,
            )
            .background(
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
                shape = RoundedCornerShape(MaterialTheme.dimens.buttonCornerRadius),
            )
            .padding(
                start = MaterialTheme.dimens.gapBetweenComponentScreen,
                end = MaterialTheme.dimens.gapBetweenComponentScreen,
            ),

        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            modifier = modifier
                .weight(1f),
            text = file.name,
            style = MaterialTheme.typography.Heading2,
            color = MaterialTheme.colorScheme.primary,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
        DeleteBtn(
            modifier = modifier
                .weight(0.2f),
            viewModel = viewModel,
            file = file
        )
    }
}