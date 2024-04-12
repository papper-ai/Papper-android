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
import java.io.File

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
        ColumnOfFiles(
            modifier = Modifier
                .weight(1f),
            viewModel = viewModel,
            list = listOf(
                File("example example example example.pdf"),
                File("example example example example.pdf"),
                File("example example example example.pdf"),
                File("example example example example.pdf"),
                File("example example example example.pdf"),
                File("example example example example.pdf"),
                File("example example example example.pdf"),
                File("example example example example.pdf"),
                File("example example example example.pdf"),
                File("example example example example.pdf"),
                File("example example example example.pdf"),
                File("example example example example.pdf"),
                File("example example example example.pdf"),
                File("example example example example.pdf"),
                File("example example example example.pdf"),
            )
        )

        Column(
            modifier = Modifier
                .padding(bottom = MaterialTheme.dimens.bottomGap3),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom,

        ) {
            AddFilesBtn()
            Spacer(modifier = Modifier.padding(top = MaterialTheme.dimens.bottomGap2))
            PageProgressComponent(
                pageCount = 2,
                currentPage = 2,
            )
            Spacer(modifier = Modifier.padding(top = MaterialTheme.dimens.bottomGap2))
            CreateStorageBtn(
                viewModel = viewModel,
                navHostController = navHostController,
            )
        }

    }
}