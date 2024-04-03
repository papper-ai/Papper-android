package com.example.papper.features.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.papper.R
import com.example.papper.theme.Heading1
import com.example.papper.theme.dimens

@Composable
fun ErrorMessageComponent(
    modifier: Modifier = Modifier,
    errorText: String,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ErrorText(text = errorText)
        Spacer(modifier = Modifier.padding(bottom = MaterialTheme.dimens.bottomGap2))
        ErrorIcon()
    }
}

@Composable
private fun ErrorText(
    modifier: Modifier = Modifier,
    text: String,
) {
    Text(
        text = text,
        style = MaterialTheme.typography.Heading1,
        color = MaterialTheme.colorScheme.onPrimary
    )
}

@Composable
private fun ErrorIcon(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(60.dp)
            .background(
                color = MaterialTheme.colorScheme.error,
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.error_icon),
            contentDescription = "Error",
            tint = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Preview
@Composable
private fun ErrorMessageComponentPreview() {
    ErrorMessageComponent(
        errorText = "Ошибка загрузки экрана"
    )
}