package com.example.papper.features.storage.storage.view.success_data

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.papper.features.storage.create_storage.view.attach_files.CreateFileBtn
import com.example.papper.features.storage.storage.presentation.StorageViewModel
import com.example.papper.theme.dimens
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun SuccessBasic(
    modifier: Modifier = Modifier,
    viewModel: StorageViewModel,
    navHostController: NavHostController,
) {
    Scaffold(
        topBar = {
            TopBar(
                navHostController = navHostController,
                title = viewModel.collectAsState().value.title,
                viewModel = viewModel,
            )
        },
        content = {
            Surface(
                modifier = modifier
                    .fillMaxSize()
                    .padding(it),
                color = MaterialTheme.colorScheme.background,
            ) {
                Column(
                    modifier = modifier
                        .fillMaxSize(),
                ) {
                    ColumnOfFiles(
                        modifier = modifier
                            .weight(1f),
                        viewModel = viewModel,
                        list = viewModel.collectAsState().value.setOfStorages.toList(),
                    )
                    Row(
                        modifier = Modifier.weight(0.1f),
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        AddFilesBtn(
                            modifier = Modifier
                                .weight(1f),
                            viewModel = viewModel,
                        )
                        CreateFileBtn(
                            modifier = Modifier
                                .weight(0.25f),
                            onClick = {
                                viewModel.navigateToCreateFileScreen()
                            }
                        )
                    }
                    Spacer(modifier = Modifier.padding(bottom = MaterialTheme.dimens.bottomGap2))
                }
            }
        },
    )
}