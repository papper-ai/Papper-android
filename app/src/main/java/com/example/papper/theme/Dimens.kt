package com.example.papper.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Dimens(
    val logoTextSize: TextUnit = 96.sp,
    val heading1TextSize: TextUnit = 24.sp,
    val heading2TextSize: TextUnit = 18.sp,
    val buttonsTextSize: TextUnit = 14.sp,
    val typingTextSize: TextUnit = 14.sp,
    val descriptionTextSize: TextUnit = 12.sp,
    val typingTextSize2: TextUnit = 32.sp,

    val gapBetweenComponents: Dp = 0.dp, //10
    val gapBetweenComponents2: Dp = 0.dp, //20
    val gapBetweenComponents3: Dp = 0.dp, //30
    val gapBetweenComponentScreen: Dp = 0.dp, //20

    val progressDotSize : Dp = 0.dp,
    val buttonsHeight: Dp = 0.dp, //55
    val buttonsWidth: Dp = 0.dp, //1
    val progressBarWeight: Dp = 0.dp, //5
    val progressBarSize: Dp = 0.dp, //50
    val sendBtnSize: Dp = 45.dp,

    val buttonGap: Dp = 0.dp, //10
    val bottomGap: Dp = 0.dp, //5
    val bottomGap2: Dp = 0.dp, //15
    val bottomGap3: Dp = 0.dp, //30

    val cardCornerRadius: Dp = 30.dp,
    val pageProgressRadius: Dp = 10.dp,
    val buttonCornerRadius: Dp = 20.dp,
    val textFieldCornerRadius: Dp = 16.dp,
)

val CompactSmallDimens = Dimens(
    logoTextSize = 80.sp,
    heading1TextSize = 20.sp,
    heading2TextSize = 16.sp,
    buttonsTextSize = 12.sp,
    typingTextSize = 12.sp,
    descriptionTextSize = 10.sp,
    typingTextSize2 = 24.sp,

    gapBetweenComponents = 5.dp,
    gapBetweenComponents2 = 10.dp,
    gapBetweenComponents3 = 20.dp,
    gapBetweenComponentScreen = 15.dp,
    progressDotSize = 8.dp,
    buttonsHeight = 45.dp,
    buttonsWidth = 1.dp,
    progressBarWeight = 5.dp,
    progressBarSize = 35.dp,
    buttonGap = 10.dp,
    bottomGap = 5.dp,
    bottomGap2 = 15.dp,
    bottomGap3 = 25.dp,

    cardCornerRadius = 20.dp,
    pageProgressRadius = 10.dp,
    buttonCornerRadius = 15.dp,
    textFieldCornerRadius = 11.dp,
)

val CompactMediumDimens = Dimens(
    gapBetweenComponents = 10.dp,
    gapBetweenComponents2 = 20.dp,
    gapBetweenComponents3 = 30.dp,
    gapBetweenComponentScreen = 20.dp,
    progressDotSize = 8.dp,
    buttonsHeight = 55.dp,
    buttonsWidth = 1.dp,
    progressBarWeight = 5.dp,
    progressBarSize = 50.dp,
    buttonGap = 10.dp,
    bottomGap = 5.dp,
    bottomGap2 = 15.dp,
    bottomGap3 = 30.dp,
)
val CompactDimens = Dimens(
    gapBetweenComponents = 10.dp,
    gapBetweenComponents2 = 20.dp,
    gapBetweenComponents3 = 30.dp,
    gapBetweenComponentScreen = 20.dp,
    progressDotSize = 8.dp,
    buttonsHeight = 55.dp,
    buttonsWidth = 1.dp,
    progressBarWeight = 5.dp,
    progressBarSize = 50.dp,
    buttonGap = 10.dp,
    bottomGap = 5.dp,
    bottomGap2 = 15.dp,
    bottomGap3 = 30.dp,
)