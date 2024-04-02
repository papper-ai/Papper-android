package com.example.papper.features.auth.registration.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.papper.features.common.components.PageProgressComponent

@Composable
fun PageProgress(
    modifier: Modifier = Modifier,
    pageCount: Int,
    currentProgress: Int,
) {
    PageProgressComponent(
        modifier = modifier,
        pageCount = pageCount,
        currentPage = currentProgress,
    )
}