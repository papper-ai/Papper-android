package com.example.papper.features.archive

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.example.papper.features.archive.presentation.ArchivesScreenState
import com.example.papper.features.archive.presentation.ArchivesViewModel
import com.example.papper.features.archive.view.TopBar
import com.example.papper.features.archive.view.error_data.ErrorBasic
import com.example.papper.features.archive.view.loading_data.LoadingBasic
import com.example.papper.features.archive.view.success_data.SuccessBasic
import org.orbitmvi.orbit.compose.collectAsState

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun ArchivesBasic(
    modifier: Modifier = Modifier,
    viewModel: ArchivesViewModel,
    navHostController: NavHostController,
) {
    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Red),
        topBar = {
            TopBar(
                navHostController = navHostController,
            )
        },
        content = {
            when (viewModel.archivesScreenState.value) {
                ArchivesScreenState.Loading -> {
                    Surface(
                        modifier = modifier
                            .fillMaxSize()
                            .padding(it),
                        color = MaterialTheme.colorScheme.background,
                    ) {
                        LoadingBasic(
                            navHostController = navHostController,
                        )
                    }
                }

                ArchivesScreenState.Success -> {
                    Surface(
                        modifier = modifier
                            .fillMaxSize()
                            .padding(it),
                        color = MaterialTheme.colorScheme.background,
                    ) {
                        SuccessBasic(
                            modifier = Modifier
                                .fillMaxSize(),
                            navHostController = navHostController,
                            list = viewModel.collectAsState().value.value.archivesState.listOfChats
                        )
                    }
                }

                ArchivesScreenState.Error -> {
                    Surface(
                        modifier = modifier
                            .fillMaxSize()
                            .padding(it),
                        color = MaterialTheme.colorScheme.background,
                    ) {
                        ErrorBasic(
                            modifier = modifier,
                            viewModel = viewModel,
                            navHostController = navHostController,
                        )
                    }
                }
            }
        },
    )
}