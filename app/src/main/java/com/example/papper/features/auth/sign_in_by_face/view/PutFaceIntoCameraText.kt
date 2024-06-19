package com.example.papper.features.auth.sign_in_by_face.view

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.papper.R
import com.example.papper.theme.Heading1
import com.example.papper.theme.Heading2
import com.example.papper.theme.dimens

@Composable
fun PutFaceIntoCameraText(
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier
            .fillMaxWidth()
            .padding(MaterialTheme.dimens.gapBetweenComponentScreen),
        text = stringResource(id = R.string.put_face_into_camera),
        style = MaterialTheme.typography.Heading2,
        color = MaterialTheme.colorScheme.onPrimary,
        textAlign = TextAlign.Center
    )
}