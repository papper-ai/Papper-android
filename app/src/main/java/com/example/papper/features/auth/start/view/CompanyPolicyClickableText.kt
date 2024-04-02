package com.example.papper.features.auth.start.view

import android.util.Log
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withAnnotation
import androidx.compose.ui.text.withStyle
import com.example.papper.theme.Description

@OptIn(ExperimentalTextApi::class)
@Composable
fun CompanyPolicyClickableText(
    modifier: Modifier = Modifier,
) {
    val annotatedString = buildAnnotatedString {
        append("Переходя дальше, ты соглашаешься на обработку \n")
        append("персональных данных в соответсвии с \n")
        withStyle(
            SpanStyle(
                textDecoration = TextDecoration.Underline
            )
        ) {
            withAnnotation(tag = "help", annotation = "vk.com") {
                append("пользовательским соглашением")
            }
        }
    }

    ClickableText(
        modifier = modifier,
        text = annotatedString,
        onClick = { offset ->
            annotatedString
                .getStringAnnotations(tag = "help", offset, offset)
                .firstOrNull()
                ?.let { annotation ->
                    annotation.item
                    //todo добавить функцию для перехода в пользовательское соглашение
                    Log.d("example", "Перешел по ссылке")
                }
        },
        style = MaterialTheme.typography.Description + TextStyle(color = MaterialTheme.colorScheme.onPrimary, textAlign = TextAlign.Center),
    )
}