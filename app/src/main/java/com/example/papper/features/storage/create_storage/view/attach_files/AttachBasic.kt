package com.example.papper.features.storage.create_storage.view.attach_files

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.papper.features.common.components.PageProgressComponent
import com.example.papper.features.storage.create_storage.presentation.CreateStorageViewModel
import com.example.papper.theme.dimens
import org.orbitmvi.orbit.compose.collectAsState

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun AttachBasic(
    modifier: Modifier = Modifier,
    viewModel: CreateStorageViewModel,
    navHostController: NavHostController,
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.padding(top = MaterialTheme.dimens.bottomGap3))
        AddFilesText()
        Spacer(modifier = Modifier.padding(top = MaterialTheme.dimens.bottomGap2))
        if (viewModel.collectAsState().value.listOfFiles.isEmpty()) {
            EmptyColumnText(
                modifier = Modifier
                    .weight(1f),
            )
        }
        else {
            ColumnOfFiles(
                modifier = Modifier
                    .weight(1f),
                viewModel = viewModel,
                list = viewModel.collectAsState().value.listOfFiles.toList()
            )
        }
        Column(
            modifier = Modifier
                .padding(bottom = MaterialTheme.dimens.bottomGap3),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom,

        ) {
            AddFilesBtn(viewModel = viewModel)
            Spacer(modifier = Modifier.padding(top = MaterialTheme.dimens.bottomGap2))
            PageProgressComponent(
                pageCount = 3,
                currentPage = 3,
            )
            Spacer(modifier = Modifier.padding(top = MaterialTheme.dimens.bottomGap2))
            CreateStorageBtn(
                viewModel = viewModel,
                navHostController = navHostController,
            )
        }

    }
}