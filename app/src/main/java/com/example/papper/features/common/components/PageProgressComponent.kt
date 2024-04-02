package com.example.papper.features.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.papper.theme.dimens

@Composable
fun PageProgressComponent(
    modifier: Modifier = Modifier,
    pageCount: Int,
    currentPage: Int,
) {
    Box(
        modifier = modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = modifier
                .wrapContentSize(),
            horizontalArrangement =  Arrangement.spacedBy(MaterialTheme.dimens.pageProgressRadius),
        ) {
            for (i in 1..pageCount) {
                if (i == currentPage) {
                    Box(
                        modifier = Modifier
                            .width(MaterialTheme.dimens.progressDotSize * 3.5f)
                            .height(MaterialTheme.dimens.progressDotSize)
                            .background(
                                color = MaterialTheme.colorScheme.onPrimaryContainer,
                                shape = RoundedCornerShape(MaterialTheme.dimens.pageProgressRadius)
                            ),
                    )
                }
                else {
                    Box(
                        modifier = Modifier
                            .size(MaterialTheme.dimens.progressDotSize)
                            .background(
                                color = MaterialTheme.colorScheme.onPrimaryContainer,
                                shape = CircleShape
                            ),
                    )
                }
            }
        }
    }
}