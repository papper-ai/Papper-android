package com.example.papper.features.storage.create_file.view.attach_photos

import android.content.pm.PackageManager
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import com.example.papper.features.common.components.PageProgressComponent
import com.example.papper.features.storage.create_file.presentation.CreateFileScreenState
import com.example.papper.features.storage.create_file.presentation.CreateFileViewModel
import com.example.papper.theme.dimens
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun AttachPhotoBasic(
    modifier: Modifier = Modifier,
    viewModel: CreateFileViewModel,
    navHostController: NavHostController,
) {
    val context = LocalContext.current
    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview(),
        onResult = { newImage ->
            if (newImage != null) {
                viewModel.attachPhotoFromCamera(newImage)
            }
        }
    )

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            cameraLauncher.launch(null)
        }
    }

    BackHandler {
        viewModel.createFileScreenState.value = CreateFileScreenState.TypingTitle
    }

    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.padding(top = MaterialTheme.dimens.bottomGap3))
        AddFilesText()
        Spacer(modifier = Modifier.padding(top = MaterialTheme.dimens.bottomGap2))
        if (viewModel.collectAsState().value.listOfPhotos.isEmpty()) {
            EmptyColumnText(
                modifier = Modifier
                    .weight(1f),
            )
        }
        else {
            ColumnOfPhotos(
                modifier = Modifier
                    .weight(1f),
                viewModel = viewModel,
                list = viewModel.collectAsState().value.listOfPhotos.toList()
            )
        }
        Column(
            modifier = Modifier
                .padding(bottom = MaterialTheme.dimens.bottomGap3),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom,

            ) {
            Row(
                horizontalArrangement = Arrangement.Center
            ) {
                AddPhotosBtn(
                    modifier = Modifier
                        .weight(1f),
                    viewModel = viewModel,
                )
                TakePhotoBtn(
                    modifier = Modifier
                        .weight(0.25f),
                    onClick = {
                        // Checks if the permission is granted
                        val permissionCheckResult =
                            ContextCompat.checkSelfPermission(
                                context,
                                android.Manifest.permission.CAMERA
                            )

                        if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {
                            // The permission is already granted
                            cameraLauncher.launch(null)
                        } else {
                            // Launches the permission request
                            permissionLauncher.launch(android.Manifest.permission.CAMERA)
                        }
                    }
                )
            }
            Spacer(modifier = Modifier.padding(top = MaterialTheme.dimens.bottomGap2))
            PageProgressComponent(
                pageCount = 3,
                currentPage = 2,
            )
            Spacer(modifier = Modifier.padding(top = MaterialTheme.dimens.bottomGap2))
            ConvertPhotosBtn(
                viewModel = viewModel,
            )
        }
    }
}