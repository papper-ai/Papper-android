package com.example.papper.features.common.components

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.papper.theme.dimens

@Composable
fun CardComponent(
    modifier: Modifier = Modifier,
    content: @Composable() (ColumnScope.() -> Unit),
) {
    Card(
        modifier = modifier
            .wrapContentHeight()
            .fillMaxWidth(1f),
        shape = RoundedCornerShape(topStart = MaterialTheme.dimens.cardCornerRadius, topEnd = MaterialTheme.dimens.cardCornerRadius),
        colors = CardColors(
            containerColor = MaterialTheme.colorScheme.onBackground,
            contentColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
            disabledContentColor = Color.Transparent,
        )
    ) {
        content()
    }
}

