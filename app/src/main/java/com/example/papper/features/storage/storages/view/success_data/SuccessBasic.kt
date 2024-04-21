package com.example.papper.features.storage.storages.view.success_data

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.papper.features.storage.storages.presentation.StoragesViewModel
import com.example.papper.theme.dimens
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun SuccessBasic(
    modifier: Modifier = Modifier,
    viewModel: StoragesViewModel,
    navHostController: NavHostController,
) {
    Column(
        modifier = modifier
            .padding(top = MaterialTheme.dimens.bottomGap)
            .fillMaxSize()
    ) {
        ColumnOfStorages(
            navHostController = navHostController,
            viewModel = viewModel,
            listOfChats = viewModel.collectAsState().value.listOfStorages,
        )
    }
}