package com.example.papper.features.auth.registration.view.all_fields

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.papper.features.common.components.CardComponent

@Composable
fun BackgroundCard(
    modifier: Modifier = Modifier,
    content: @Composable() (ColumnScope.() -> Unit),
) {
    CardComponent(
        modifier = modifier,
    ) {
        content()
    }
}