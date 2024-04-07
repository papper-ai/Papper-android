package com.example.papper.features.common.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.papper.R
import com.example.papper.theme.dimens

@Composable
fun TopBarWithLogoComponent(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalAlignment = Alignment.Bottom
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.BottomCenter
        ) {
            SmallLogoComponent()
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = MaterialTheme.dimens.gapBetweenComponentScreen),
            ) {
                BackBtn(
                    onClick = onClick,
                )
            }
        }
    }
}

@Preview
@Composable
private fun TopBarWithLogoComponentPreview() {
    TopBarWithLogoComponent(onClick = {})
}

@Composable
private fun BackBtn(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .size(48.dp)
            .clip(CircleShape)
            .clickable {
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painterResource(id = R.drawable.back_icon),
            contentDescription = "BackBtn",
            tint = MaterialTheme.colorScheme.onPrimary,
        )
    }
}