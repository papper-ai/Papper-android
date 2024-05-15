package com.example.papper.features.chat.chat.view.success_data.not_empty_chat

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.papper.features.chat.chat.model.Message
import com.example.papper.features.chat.chat.model.MessageSender
import com.example.papper.theme.Heading1
import com.example.papper.theme.Heading2
import com.example.papper.theme.dimens
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MessageItem(
    modifier: Modifier = Modifier,
    message: Message,
) {
    val scope = rememberCoroutineScope()
    var isBottomSheetVisible by rememberSaveable { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    BottomSheetContent(
        isBottomSheetVisible = isBottomSheetVisible,
        sheetState = sheetState,
        onDismiss = {
            scope.launch { sheetState.hide() }
                .invokeOnCompletion { isBottomSheetVisible = false }
        },
        message = message,
    )

    Box(
        modifier = modifier
            .fillMaxWidth(),
        contentAlignment = if (message.from == MessageSender.Bot) Alignment.CenterStart else Alignment.CenterEnd,
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth(0.9f)
                .clickable(
                    enabled = message.traceBack != null
                ) {
                    if (message.traceBack != null) {
                        scope.launch {
                            isBottomSheetVisible = true
                            sheetState.expand()
                        }
                    }
                },
            contentAlignment = if (message.from == MessageSender.Bot) Alignment.CenterStart else Alignment.CenterEnd,
        ) {
            Box(
                modifier = modifier
                    .padding(bottom = MaterialTheme.dimens.bottomGap)
                    .wrapContentSize()
                    .background(
                        color = if (message.from == MessageSender.Bot) MaterialTheme.colorScheme.onBackground else MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(
                            topStart = MaterialTheme.dimens.textFieldCornerRadius,
                            topEnd = MaterialTheme.dimens.textFieldCornerRadius,
                            bottomStart = if (message.from == MessageSender.Bot) 0.dp else MaterialTheme.dimens.textFieldCornerRadius,
                            bottomEnd = if (message.from == MessageSender.Bot) MaterialTheme.dimens.textFieldCornerRadius else 0.dp,
                        )
                    )
                    .padding(
                        top = MaterialTheme.dimens.bottomGap2,
                        bottom = MaterialTheme.dimens.bottomGap2,
                        start = MaterialTheme.dimens.gapBetweenComponents,
                        end = MaterialTheme.dimens.gapBetweenComponents,
                    ),
                contentAlignment = Alignment.Center
            ) {
                SelectionContainer {
                    MessageText(text = message.text)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetContent(
    isBottomSheetVisible: Boolean,
    sheetState: SheetState,
    onDismiss: () -> Unit,
    message: Message,
) {

    if (isBottomSheetVisible) {
        ModalBottomSheet(
            onDismissRequest = onDismiss,
            sheetState = sheetState,
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            shape = RoundedCornerShape(MaterialTheme.dimens.cardCornerRadius),
            dragHandle = null,
            windowInsets = WindowInsets(0, 0, 0, 0)
        ) {
            Box(
                modifier = Modifier
                    .statusBarsPadding()
                    .fillMaxWidth()
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                FilledIconButton(
                    modifier = Modifier.size(48.dp),
                    onClick = onDismiss,
                    colors = IconButtonDefaults.filledIconButtonColors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer
                    )
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Close,
                        contentDescription = "Hide the dialog.",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }

            LazyColumn(
                modifier = Modifier
                    .navigationBarsPadding()
                    .clip(
                        shape = RoundedCornerShape(
                            topStart = MaterialTheme.dimens.cardCornerRadius,
                            topEnd = MaterialTheme.dimens.cardCornerRadius
                        )
                    )
                    .background(color = MaterialTheme.colorScheme.onBackground)
                    .fillMaxWidth()
                    .padding(
                        start = MaterialTheme.dimens.gapBetweenComponentScreen,
                        end = MaterialTheme.dimens.gapBetweenComponentScreen,
                        top = 0.dp,
                    )
            ) {
                item {
                    Spacer(modifier = Modifier.height(MaterialTheme.dimens.bottomGap2))
                    for (traceBack in message.traceBack!!) {
                        Text(
                            text = traceBack.documentId,
                            color = MaterialTheme.colorScheme.onPrimary,
                            style = MaterialTheme.typography.Heading1
                        )
                        Spacer(modifier = Modifier.height(MaterialTheme.dimens.bottomGap))
                        if (traceBack.information != null) {
                            Text(
                                text = traceBack.information,
                                color = MaterialTheme.colorScheme.onPrimary,
                                style = MaterialTheme.typography.Heading2
                            )
                        }
                        Spacer(modifier = Modifier.height(MaterialTheme.dimens.bottomGap2))
                    }
                }
            }
        }
    }
}
