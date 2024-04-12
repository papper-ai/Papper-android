package com.example.papper.features.storage.storages.view.success_data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.papper.navigation.Screens

@Composable
fun FloatingBtn(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
) {
    FloatingActionButton(
        onClick = {
            navHostController.navigate(Screens.CreateStorageScreen.route)
        },
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary,
        content = {
            Icon(Icons.Rounded.Add , contentDescription = "add_storage")
        },
    )
}