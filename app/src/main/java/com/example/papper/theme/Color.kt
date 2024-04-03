package com.example.papper.theme

import androidx.compose.ui.graphics.Color

data class PapperColors(
    val primaryBackgroundColor: Color,
    val secondaryBackgroundColor: Color,
    val primaryTextColor: Color,
    val hintColor: Color,
    val errorColor: Color,
    val primaryButtonColor: Color,
    val secondaryButtonColor: Color,
    val disabledButtonColor: Color,
    val disabledTextColor: Color,
    val chatBotColor: Color,
)

val darkPallet = PapperColors(
    primaryBackgroundColor = Color(0xFF181818),
    secondaryBackgroundColor = Color(0xFF292929),
    primaryTextColor = Color(0xFFFFFFFF),
    hintColor = Color(0xFFAAAAAA),
    errorColor = Color(0xFFE63D43),
    primaryButtonColor = Color(0xFF008000),
    secondaryButtonColor = Color(0xFF292929),
    disabledButtonColor = Color(0xFF7E7E7E),
    disabledTextColor = Color(0xFF555555),
    chatBotColor = Color(0xFF8D0088)
)

//TODO добавить светлую тему
val lightPallet = PapperColors(
    primaryBackgroundColor = Color(0xFF181818),
    secondaryBackgroundColor = Color(0xFF292929),
    primaryTextColor = Color(0xFFFFFFFF),
    hintColor = Color(0xFFAAAAAA),
    errorColor = Color(0xFFE63D43),
    primaryButtonColor = Color(0xFF008000),
    secondaryButtonColor = Color(0xFF292929),
    disabledButtonColor = Color(0xFF7E7E7E),
    disabledTextColor = Color(0xFF555555),
    chatBotColor = Color(0xFF8D0088)
)