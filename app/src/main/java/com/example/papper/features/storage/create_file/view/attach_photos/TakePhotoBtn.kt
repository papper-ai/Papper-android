package com.example.papper.features.storage.create_file.view.attach_photos

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.papper.features.common.components.SmallStrokeButtonComponent
import com.example.papper.theme.dimens

@Composable
fun TakePhotoBtn(
    modifier: Modifier = Modifier,
    onClick : () -> Unit,
) {
    SmallStrokeButtonComponent(
        modifier = modifier
            .padding(start = 5.dp, end = MaterialTheme.dimens.gapBetweenComponentScreen),
        onClick = {
            onClick()
        }
    )
}
