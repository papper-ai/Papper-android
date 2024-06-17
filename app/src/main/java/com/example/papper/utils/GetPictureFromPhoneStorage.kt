package com.example.papper.utils

import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.core.content.ContextCompat
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date

@Composable
fun getPermissionLauncher(granted: () -> Unit) = rememberLauncherForActivityResult(
    ActivityResultContracts.RequestPermission()
) { isGranted ->
    if (isGranted) {
        granted()
    }
}

@Composable
fun getCameraLauncher(
    onResult: () -> Unit,
) = rememberLauncherForActivityResult(
    contract = ActivityResultContracts.TakePicture(),
    onResult = {
        onResult()
    }
)

fun checkPermissions(
    context: Context,
    granted: () -> Unit,
    notGranted: () -> Unit,
) {
    //Checks if the permission is granted
    val permissionCheckResult =
        ContextCompat.checkSelfPermission(
            context,
            android.Manifest.permission.CAMERA
        )

    if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {
        // The permission is already granted
        granted()
    } else {
        // Launches the permission request
        notGranted()
    }
}

@Composable
fun getFilePickerLauncher(
    onResult: (uri: Uri) -> Unit,
) = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { photoUri ->
            if (photoUri != null) {
                onResult(photoUri)
            }
        }
    )

@Composable
fun getMultipleFilePickerLauncher(
    onResult: (list: List<Uri>) -> Unit,
) = rememberLauncherForActivityResult(
    contract = ActivityResultContracts.GetMultipleContents(),
    onResult = { list ->
        onResult(list)
    },
)

fun Context.createImageFile(): File {
    val timeStamp = SimpleDateFormat("yyyy_MM_dd").format(Date())
    val imageFileName = "JPEG_" + timeStamp
    val image = File.createTempFile(
        imageFileName,
        ".jpg",
        externalCacheDir
    )

    return image
}