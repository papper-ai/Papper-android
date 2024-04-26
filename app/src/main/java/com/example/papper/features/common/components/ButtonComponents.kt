package com.example.papper.features.common.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.papper.theme.Heading2
import com.example.papper.theme.dimens

@Composable
private fun ButtonTemplate(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    status: Boolean,
    isLoading: Boolean,
    text: String,
    btnColor: Color,
    borderColor: Color,
    disabledColor: Color,
    disabledTextColor: Color,
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .height(MaterialTheme.dimens.buttonsHeight)
            .padding(
                start = MaterialTheme.dimens.gapBetweenComponentScreen,
                end = MaterialTheme.dimens.gapBetweenComponentScreen
            ),
        onClick = {
            onClick()
        },
        enabled = status,
        shape = RoundedCornerShape(MaterialTheme.dimens.buttonCornerRadius),
        colors = ButtonDefaults.buttonColors (
            containerColor = btnColor,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            disabledContainerColor = disabledColor,
            disabledContentColor = disabledTextColor,
        ),
        border = BorderStroke(MaterialTheme.dimens.buttonsWidth, color = borderColor),
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier
                    .size(MaterialTheme.dimens.buttonCornerRadius),
                color = MaterialTheme.colorScheme.onPrimary,
                strokeWidth = MaterialTheme.dimens.buttonsWidth + MaterialTheme.dimens.buttonsWidth
            )
        }
        else {
            Text(
                text = text,
                style = MaterialTheme.typography.Heading2,
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Composable
fun ButtonComponent(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    isEnable: Boolean = true,
    isLoading: Boolean = false,
    text: String,
) {
    ButtonTemplate(
        modifier = modifier,
        onClick = onClick,
        status = isEnable,
        isLoading = isLoading,
        text = text,
        btnColor = MaterialTheme.colorScheme.primary,
        borderColor = Color.Transparent,
        disabledColor = MaterialTheme.colorScheme.secondaryContainer,
        disabledTextColor = MaterialTheme.colorScheme.onSecondaryContainer,
    )
}

@Composable
fun OutlinedButtonComponent (
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    isEnable: Boolean = true,
    isLoading: Boolean = false,
    text: String,
) {
    ButtonTemplate(
        modifier = modifier,
        onClick = onClick,
        status = isEnable,
        isLoading = isLoading,
        text = text,
        btnColor = Color.Transparent,
        borderColor = MaterialTheme.colorScheme.onPrimary,
        disabledColor = Color.Transparent,
        disabledTextColor = Color.Transparent,
    )
}

@Composable
fun StrokeButtonComponent(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    text: String,
    isLoading: Boolean = false,
) {
    val stroke = Stroke(
        width = 4f,
        pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
    )
    val borderColor = MaterialTheme.colorScheme.onPrimary
    val cornerRadiusBtn = MaterialTheme.dimens.buttonCornerRadius

    Box(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(
                start = MaterialTheme.dimens.gapBetweenComponentScreen * 2,
                end = MaterialTheme.dimens.gapBetweenComponentScreen * 2,
            )
            .drawBehind {
                drawRoundRect(
                    color = borderColor,
                    style = stroke,
                    cornerRadius = CornerRadius(cornerRadiusBtn.toPx())
                )
            }
            .clip(RoundedCornerShape(MaterialTheme.dimens.buttonCornerRadius))
            .clickable { onClick() },
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .heightIn(min = MaterialTheme.dimens.buttonsHeight, max = MaterialTheme.dimens.buttonsHeight),
            contentAlignment = Alignment.Center
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = modifier
                        .size(14.dp),
                    color = MaterialTheme.colorScheme.onPrimary,
                    strokeWidth = 2.dp
                )
            }
            else {
                Text(
                    modifier = modifier,
                    text = text,
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = MaterialTheme.typography.Heading2,
                    textAlign = TextAlign.Center
                )
            }

        }
    }
}

@Composable
fun SmallButtonComponent(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String,
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .height(MaterialTheme.dimens.buttonsHeight)
            .padding(
                start = MaterialTheme.dimens.gapBetweenComponentScreen * 3.5f,
                end = MaterialTheme.dimens.gapBetweenComponentScreen * 3.5f,
            ),
        onClick = {
            onClick()
        },
        colors = ButtonDefaults.buttonColors (
            containerColor = MaterialTheme.colorScheme.onSecondaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ),
        shape = RoundedCornerShape(MaterialTheme.dimens.buttonCornerRadius),
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.Heading2,
            textAlign = TextAlign.Center
        )
    }
}