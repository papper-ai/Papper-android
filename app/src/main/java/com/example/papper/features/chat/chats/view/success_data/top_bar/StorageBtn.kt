package com.example.papper.features.chat.chats.view.success_data.top_bar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.papper.R
import com.example.papper.navigation.Screens

@Composable
fun StorageBtn(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
) {
    Box(
        modifier = modifier
            .size(48.dp)
            .clip(CircleShape)
            .clickable {
                navHostController.navigate(Screens.StoragesScreen.route)
            },
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            painter = painterResource(id = R.drawable.storage_icon),
            contentDescription = "Storage",
            tint = MaterialTheme.colorScheme.onPrimary,
        )
    }
}