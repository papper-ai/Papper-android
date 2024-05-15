package com.example.papper.features.chat.create_chat.view.choose_storage.attach_files

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import com.example.papper.features.storage.storage.model.FilePresentationModel
import com.example.papper.theme.Heading2
import com.example.papper.theme.dimens

@Composable
fun ColumnOfFiles(
    modifier: Modifier = Modifier,
    listOfFiles: List<FilePresentationModel>?,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
    ) {
        if (listOfFiles != null) {
            items(listOfFiles) { file ->
                ItemBasic(item = file)
            }
        }
    }
}

@Composable
fun ItemBasic(
    modifier: Modifier = Modifier,
    item: FilePresentationModel,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier
                .padding(
                    start = MaterialTheme.dimens.gapBetweenComponentScreen,
                    end = MaterialTheme.dimens.gapBetweenComponentScreen,
                    bottom = MaterialTheme.dimens.bottomGap,
                ),
            text = item.title,
            style = MaterialTheme.typography.Heading2,
            color = MaterialTheme.colorScheme.onPrimary,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}