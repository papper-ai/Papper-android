package com.example.papper.features.storage.create_file.view

import android.Manifest
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityOptionsCompat
import androidx.core.content.FileProvider
import coil.compose.rememberImagePainter
import com.example.papper.R
import com.example.papper.features.common.components.ImageAlertDialog
import com.example.papper.features.storage.create_file.model.PhotoModel
import com.example.papper.features.storage.create_file.presentation.CreateFileViewModel
import com.example.papper.theme.Heading1
import com.example.papper.theme.dimens
import com.example.papper.utils.GetFile.getFile
import com.example.papper.utils.checkPermissions
import com.example.papper.utils.createImageFile
import com.example.papper.utils.getCameraLauncher
import com.example.papper.utils.getFilePickerLauncher
import com.example.papper.utils.getPermissionLauncher
import java.util.Objects

@Composable
fun PhotoItemBasic(
    modifier: Modifier = Modifier,
    viewModel: CreateFileViewModel,
    photo: PhotoModel,
    onDeleteClick: () -> Unit,
) {
    var showImage by remember {
        mutableStateOf(false)
    }

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
            if (photo.text.isEmpty()) {
                viewModel.replacePhoto(
                    oldPhoto = photo,
                    newPhoto = PhotoModel(
                        id = photo.id,
                        imageUri = uri,
                        text = "",
                    )
                )
            } else {
                viewModel.reconvertPhoto(
                    oldPhoto = photo,
                    newPhoto = PhotoModel(
                        id = photo.id,
                        imageUri = uri,
                        text = "",
                    )
                )
            }
        }
    )

    val permissionLauncher = getPermissionLauncher(
        granted = {
            cameraLauncher.launch(uri)
        }
    )

    val filePickerLauncher = getFilePickerLauncher(
        onResult = { photoUri ->
            val path = photoUri.getFile(context)?.path
            if (path != null) {
                if (photo.text.isEmpty()) {
                    viewModel.replacePhoto(
                        oldPhoto = photo,
                        newPhoto = PhotoModel(
                            id = photo.id,
                            imageUri = photoUri,
                            text = "",
                        )
                    )
                } else {
                    viewModel.reconvertPhoto(
                        oldPhoto = photo,
                        newPhoto = PhotoModel(
                            id = photo.id,
                            imageUri = photoUri,
                            text = "",
                        )
                    )
                }
            }
        }
    )

    Column(
        modifier = modifier
            //.width(IntrinsicSize.Max)
            .fillMaxWidth(0.45f)
            .wrapContentHeight()
            .clickable {
                showImage = true
            },
        verticalArrangement = Arrangement.Top
    ) {
        Box(
            modifier = Modifier
                .wrapContentSize(),
            contentAlignment = Alignment.TopCenter
        ) {
            Image(
                modifier = Modifier
                    .height(225.dp)
                    .fillMaxWidth()
                    .padding(start = 5.dp, end = 5.dp, top = 5.dp)
                    .clip(shape = RoundedCornerShape(MaterialTheme.dimens.buttonCornerRadius))
                    .background(color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.2f)),
                painter = rememberImagePainter(photo.imageUri),
                contentDescription = "image",
                contentScale = ContentScale.FillBounds
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier
                        .size(30.dp)
                        .background(color = MaterialTheme.colorScheme.primary, shape = CircleShape),
                    contentAlignment = Alignment.TopStart
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 4.dp),
                        text = photo.id.toString(),
                        style = MaterialTheme.typography.Heading1,
                        color = MaterialTheme.colorScheme.onPrimary,
                        textAlign = TextAlign.Center
                    )
                }

                IconButton(
                    modifier = Modifier.size(30.dp),
                    onClick = {
                        onDeleteClick()
                    },
                    colors = IconButtonColors(
                        containerColor = MaterialTheme.colorScheme.error,
                        contentColor = MaterialTheme.colorScheme.onPrimary,
                        disabledContainerColor = MaterialTheme.colorScheme.onSecondaryContainer,
                        disabledContentColor = MaterialTheme.colorScheme.onPrimary
                    ),
                    content = {
                        Icon(
                            painter = painterResource(id = R.drawable.error_icon_small),
                            contentDescription = "delete",
                        )
                    }
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(230.dp)
                    .padding(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.Bottom,
            ) {
                Box(
                    modifier = Modifier
                        .size(30.dp)
                        .background(color = MaterialTheme.colorScheme.primary, shape = CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.edit_icon),
                        contentDescription = "edit",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        }
    }

    if (showImage) {
        ImageAlertDialog(
            onDismissRequest = {
                showImage = false
            },
            isWithReplaceImage = true,
            onReplaceClick = {
                filePickerLauncher.launch("image/*", options = ActivityOptionsCompat.makeBasic())
            },
            onGetPhotoClick = {
                checkPermissions(
                    granted = {
                        cameraLauncher.launch(uri)
                    },
                    notGranted = {
                        permissionLauncher.launch(Manifest.permission.CAMERA)
                    },
                    context = context,
                )
            },
            image = photo.imageUri,
        )
    }
}

