package com.example.papper.features.storage.create_file.view.attach_photos

import android.net.Uri
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.FileProvider
import com.example.papper.features.storage.create_file.presentation.CreateFileViewModel
import com.example.papper.theme.dimens
import com.example.papper.utils.checkPermissions
import com.example.papper.utils.createImageFile
import com.example.papper.utils.getCameraLauncher
import com.example.papper.utils.getPermissionLauncher
import org.orbitmvi.orbit.compose.collectAsState
import java.util.Objects

@Composable
fun AttachPhotoBasic(
    modifier: Modifier = Modifier,
    viewModel: CreateFileViewModel,
    onBackHandler: () -> Unit,
) {
    val context = LocalContext.current

    val file = context.createImageFile()
    val uri = FileProvider.getUriForFile(
        Objects.requireNonNull(context),
        context.packageName + ".provider", file
    )
    var capturedImageUri = remember {
        mutableStateOf<Uri>(Uri.EMPTY)
    }

    val cameraLauncher = getCameraLauncher(
        onResult = {
            capturedImageUri.value = uri
            viewModel.addPhoto(uri)
        }
    )

    val permissionLauncher = getPermissionLauncher(
        granted = {
            cameraLauncher.launch(uri)
        }
    )

    BackHandler {
        onBackHandler()
    }

    Column(
        modifier = modifier,
            //.fillMaxSize(),
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
                        checkPermissions(
                            granted = {
                                cameraLauncher.launch(uri)
                            },
                            notGranted = {
                                permissionLauncher.launch(android.Manifest.permission.CAMERA)
                            },
                            context = context,
                        )
                    }
                )
            }
            Spacer(modifier = Modifier.padding(top = MaterialTheme.dimens.bottomGap2))
        }
    }

}