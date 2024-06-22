package com.example.papper.features.storage.storages.view.success_data

import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.papper.R
import com.example.papper.features.storage.storages.presentation.StoragesViewModel

@Composable
fun FloatingBtn(
    modifier: Modifier = Modifier,
    viewModel: StoragesViewModel,
) {
    FloatingActionButton(
        onClick = {
            viewModel.navigateToCreateStorageScreen()
        },
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary,
        content = {
            Icon(
                painter = painterResource(id = R.drawable.add_icon),
                contentDescription = "add_storage",
            )
        },
    )
}