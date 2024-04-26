package com.example.papper.features.common.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.papper.R
import com.example.papper.theme.Heading1
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


@Composable
fun TopBarWithTitleSettingsComponent(
    modifier: Modifier = Modifier,
    onBackBtnClick: () -> Unit,
    onSettingsBtnClick: () -> Unit,
    text: String,
) {
    Row(
        modifier = modifier
            .padding(top = MaterialTheme.dimens.gapBetweenComponents, bottom = MaterialTheme.dimens.bottomGap)
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .weight(0.3f)
                .padding(start = MaterialTheme.dimens.gapBetweenComponentScreen),
        ) {
            BackBtn(
                onClick = onBackBtnClick,
            )
        }

        Text(
            modifier = modifier
                .fillMaxWidth()
                .weight(1f),
            text = text,
            style = MaterialTheme.typography.Heading1,
            color = MaterialTheme.colorScheme.onPrimary,
            textAlign = TextAlign.Center,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )

        Box(
            modifier = modifier
                .fillMaxWidth()
                .weight(0.3f)
                .padding(end = MaterialTheme.dimens.gapBetweenComponentScreen),
            contentAlignment = Alignment.BottomEnd
        ) {
            SettingsBtn(
                onClick = onSettingsBtnClick,
            )
        }
    }

}


@Preview
@Composable
private fun TopBarWithLogoComponentPreview() {
    TopBarWithLogoComponent(onClick = {})
}

@Preview
@Composable
private fun TopBarWithTitleSettingsComponentPreview() {
    TopBarWithTitleSettingsComponent(onBackBtnClick = {}, onSettingsBtnClick = {}, text = "Title Title Title Title Title Title Title")
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

@Composable
private fun SettingsBtn(
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
            Icons.Rounded.MoreVert,
            contentDescription = "SettingsBtn",
            tint = MaterialTheme.colorScheme.onPrimary,
        )
    }
}