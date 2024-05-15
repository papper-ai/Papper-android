package com.example.papper.features.chat.chat.view.success_data.not_empty_chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.papper.features.chat.chat.model.Message
import com.example.papper.features.chat.chat.model.MessageSender
import com.example.papper.theme.dimens

@Composable
fun MessageItem(
    modifier: Modifier = Modifier,
    message: Message,
) {
    Box (
        modifier = modifier
            .fillMaxWidth(),
        contentAlignment = if (message.from == MessageSender.Bot) Alignment.CenterStart else Alignment.CenterEnd,
    ){
        Box(
            modifier = modifier
                .fillMaxWidth(0.9f),
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