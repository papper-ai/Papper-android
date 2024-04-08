package com.example.papper.features.common.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import com.example.papper.theme.Heading2
import com.example.papper.theme.dimens

@Composable
fun RadioBtnComponent(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String,
    isSelected: Boolean,
) {
    if (isSelected) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(MaterialTheme.dimens.buttonsHeight)
                .padding(
                    start = MaterialTheme.dimens.gapBetweenComponentScreen * 3.5f,
                    end = MaterialTheme.dimens.gapBetweenComponentScreen * 3.5f,
                )
                .background(
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
                    shape = RoundedCornerShape(MaterialTheme.dimens.buttonCornerRadius),
                )
                .noRippleClickable {
                    onClick()
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.Heading2,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
    else {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(MaterialTheme.dimens.buttonsHeight)
                .padding(
                    start = MaterialTheme.dimens.gapBetweenComponentScreen * 3.5f,
                    end = MaterialTheme.dimens.gapBetweenComponentScreen * 3.5f,
                )
                .background(
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    shape = RoundedCornerShape(MaterialTheme.dimens.buttonCornerRadius),
                )
                .noRippleClickable {
                    onClick()
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.Heading2,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}

@SuppressLint("ModifierFactoryUnreferencedReceiver")
private fun Modifier.noRippleClickable(onClick: () -> Unit): Modifier = composed {
    clickable(
        indication = null,
        interactionSource = remember { MutableInteractionSource() }) {
        onClick()
    }
}