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
import androidx.compose.ui.tooling.preview.Preview
import com.example.papper.theme.Heading2
import com.example.papper.theme.dimens
import java.io.File

@Composable
fun ColumnOfFiles(
    modifier: Modifier = Modifier,
    listOfFiles: List<File>?,
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
    item: File,
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
            text = item.name,
            style = MaterialTheme.typography.Heading2,
            color = MaterialTheme.colorScheme.onPrimary,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Preview
@Composable
fun ColumnOfFilesPreview() {
    ColumnOfFiles(
        listOfFiles = listOf(File("item1item1item1item1item1item1item1item1item1"), File("item2"), File("item3")),
    )
}