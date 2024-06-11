package com.example.papper.features.storage.create_file.view.attach_photos

import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
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
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityOptionsCompat
import androidx.core.content.ContextCompat
import com.example.papper.R
import com.example.papper.features.common.components.ImageAlertDialog
import com.example.papper.features.storage.create_file.model.AttachPhotoModel
import com.example.papper.features.storage.create_file.presentation.CreateFileViewModel
import com.example.papper.theme.Heading1
import com.example.papper.theme.dimens
import com.example.papper.utils.GetFile.getFile

@Composable
fun PhotoItemBasic(
    modifier: Modifier = Modifier,
    viewModel: CreateFileViewModel,
    photo: AttachPhotoModel,
) {
    var showImage by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable {
                showImage = true
            },
        verticalArrangement = Arrangement.Top
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Image(
                modifier = Modifier
                    .height(225.dp)
                    .fillMaxWidth()
                    .padding(start = 5.dp, end = 5.dp, top = 5.dp)
                    .clip(shape = RoundedCornerShape(MaterialTheme.dimens.buttonCornerRadius)),
                bitmap = photo.image.asImageBitmap(),
                contentDescription = "image",
            )

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

            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.TopEnd
            ) {
                IconButton(
                    modifier = Modifier.size(30.dp),
                    onClick = {
                        viewModel.deletePhoto(photo = photo)
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
        }
    }

    val context = LocalContext.current
    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview(),
        onResult = { newImage ->
            if (newImage != null) {
                viewModel.replacePhoto(
                    oldPhoto = photo,
                    newPhoto = AttachPhotoModel(
                        id = photo.id,
                        image = newImage
                    )
                )
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

    val selectFilesActivity = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { photoUri ->
        val path = photoUri?.getFile(context)?.path
        if (path != null) {
            viewModel.replacePhoto(
                oldPhoto = photo,
                newPhoto = AttachPhotoModel(
                    id = photo.id,
                    image = BitmapFactory.decodeFile(path)
                )
            )
        }
    }

    if (showImage) {
        ImageAlertDialog(
            onDismissRequest = {
                showImage = false
            },
            isWithReplaceImage = true,
            onReplaceClick = {
                selectFilesActivity.launch("image/*", options = ActivityOptionsCompat.makeBasic())
            },
            onGetPhotoClick = {
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
            },
            image = photo.image,
        )
    }
}