package com.example.papper.features.storage.storages.view.success_data.storage_item

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.papper.R
import com.example.papper.theme.dimens

@Composable
fun StorageIcon(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .size(75.dp)
            .background(
                color = MaterialTheme.colorScheme.secondary,
                shape = RoundedCornerShape(MaterialTheme.dimens.buttonCornerRadius)
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.storage_item_icon),
            contentDescription = "StorageIcon",
            tint = MaterialTheme.colorScheme.onPrimary,
        )
    }
}