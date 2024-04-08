package com.example.papper.features.common.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
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
        )
    }
}