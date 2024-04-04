package com.example.papper.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember

@Composable
fun AppUtils(
    appDimens: Dimens,
    content: @Composable () -> Unit,
) {
    val appDimens = remember {
        appDimens
    }
    
    CompositionLocalProvider(localAppDimens provides appDimens) {
        content()
    }
}

val localAppDimens = compositionLocalOf {
    CompactDimens
}