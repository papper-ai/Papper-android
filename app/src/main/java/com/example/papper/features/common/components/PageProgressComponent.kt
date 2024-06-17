package com.example.papper.features.common.components

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.example.papper.theme.dimens

@Composable
fun PageProgressComponent(
    modifier: Modifier = Modifier,
    pageCount: Int,
    currentPage: Int,
    selectedColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
    defaultColor: Color = MaterialTheme.colorScheme.onSecondaryContainer,
    defaultRadius: Dp = MaterialTheme.dimens.progressDotSize,
    selectedLength: Dp = MaterialTheme.dimens.progressDotSize * 3.5f,
    space: Dp = MaterialTheme.dimens.pageProgressRadius,
) {
    Box(
        modifier = modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(space),
            modifier = modifier
        ) {
            repeat(pageCount) {
                Indicator(
                    isSelected = it == currentPage - 1,
                    selectedColor = selectedColor,
                    defaultColor = defaultColor,
                    defaultRadius = defaultRadius,
                    selectedLength = selectedLength,
                )
            }
        }
    }
}

/**
 * pager indicator item
 */
@Composable
private fun Indicator(
    defaultRadius: Dp,
    modifier: Modifier = Modifier.height(defaultRadius),
    isSelected: Boolean,
    selectedColor: Color,
    defaultColor: Color,
    selectedLength: Dp,
) {
    val width by animateDpAsState(
        targetValue = if (isSelected) selectedLength else defaultRadius,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy)
    )
    Box(
        modifier = modifier
            .width(width)
            .clip(CircleShape)
            .background(color = if (isSelected) selectedColor else defaultColor)
    )
}

//@Composable
//fun PageProgressComponent(
//    modifier: Modifier = Modifier,
//    pageCount: Int,
//    currentPage: Int,
//) {
//    Box(
//        modifier = modifier
//            .fillMaxWidth(),
//        contentAlignment = Alignment.Center
//    ) {
//        Row(
//            modifier = modifier
//                .wrapContentSize(),
//            horizontalArrangement =  Arrangement.spacedBy(MaterialTheme.dimens.pageProgressRadius),
//        ) {
//            for (i in 1..pageCount) {
//                if (i == currentPage) {
//                    Box(
//                        modifier = Modifier
//                            .width(MaterialTheme.dimens.progressDotSize * 3.5f)
//                            .height(MaterialTheme.dimens.progressDotSize)
//                            .background(
//                                color = MaterialTheme.colorScheme.onPrimaryContainer,
//                                shape = RoundedCornerShape(MaterialTheme.dimens.pageProgressRadius)
//                            ),
//                    )
//                }
//                else {
//                    Box(
//                        modifier = Modifier
//                            .size(MaterialTheme.dimens.progressDotSize)
//                            .background(
//                                color = MaterialTheme.colorScheme.onPrimaryContainer,
//                                shape = CircleShape
//                            ),
//                    )
//                }
//            }
//        }
//    }
//}