package com.example.papper.features.chat.chat.view.success_data.not_empty_chat

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.example.papper.features.chat.chat.model.Message
import com.example.papper.theme.dimens
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition", "StateFlowValueCalledInComposition")
@Composable
fun MessagesColumn(
    modifier: Modifier = Modifier,
    list: List<Message>
) {
    val coroutineScope = rememberCoroutineScope()
    val listState = rememberLazyListState()

    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(
            start = MaterialTheme.dimens.gapBetweenComponentScreen,
            end = MaterialTheme.dimens.gapBetweenComponentScreen,
        ),
        state = listState,
    ) {
        coroutineScope.launch {
            listState.scrollToItem(list.lastIndex)
        }
        items(
            items = list,
        ) { message ->
            MessageItem(
                message = message,
            )
        }
    }
}