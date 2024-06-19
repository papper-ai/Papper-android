package com.example.papper.features.auth.sign_in_by_face

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Matrix
import android.util.Log
import android.util.Size
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.ImageProxy
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.example.papper.features.auth.sign_in_by_face.presentation.SignInByFaceViewModel
import com.example.papper.features.auth.sign_in_by_face.view.CameraPreview
import com.example.papper.features.auth.sign_in_by_face.view.PutFaceIntoCameraText
import com.example.papper.features.auth.sign_in_by_face.view.TopBar
import com.example.papper.theme.dimens
import org.orbitmvi.orbit.compose.collectAsState

@SuppressLint("MutableCollectionMutableState")
@Composable
fun SignInByFaceBasic(
    modifier: Modifier = Modifier,
    viewModel: SignInByFaceViewModel,
) {
    //val scrollState = rememberScrollState()

    val context = LocalContext.current
    val controller = remember {
        LifecycleCameraController(context.applicationContext).apply {
            setEnabledUseCases(
                CameraController.IMAGE_CAPTURE
            )
        }
    }
    controller.cameraSelector = CameraSelector.DEFAULT_FRONT_CAMERA
    controller.imageCaptureTargetSize = CameraController.OutputSize(Size(640, 480))
    controller.previewTargetSize = CameraController.OutputSize(Size(640, 480))

    Scaffold(
        modifier = modifier,
        topBar = {
            TopBar(viewModel = viewModel)
        },
        content = {
            Column(
                modifier = modifier
                    .padding(it)
                    .fillMaxSize(),
                    //.verticalScroll(scrollState),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Spacer(modifier = Modifier.height(MaterialTheme.dimens.gapBetweenComponents))
                PutFaceIntoCameraText(
                    modifier = Modifier
                        .padding(horizontal = MaterialTheme.dimens.gapBetweenComponentScreen)
                )
                Spacer(modifier = Modifier.height(MaterialTheme.dimens.gapBetweenComponents2))
                CameraPreview(
                    modifier = Modifier
                        .size(275.dp)
                        .clip(CircleShape)
                        .border(4.dp, MaterialTheme.colorScheme.onPrimary, CircleShape),
                    controller = controller,
                )
                Button(
                    onClick = {
                        takePhoto(
                            controller = controller,
                            onPhotoTaken = {
                                viewModel.addItem(it)
                            },
                            context = context
                        )
                    }
                ) {

                }

                val list = viewModel.collectAsState().value.list
                if (list.isNotEmpty()) {
                    LazyColumn(
                        modifier = Modifier
                            .wrapContentSize()
                            .background(color = Color.Red),
                    ) {
                        items(list) { bitmap ->
                            Image(
                                modifier = Modifier
                                    .wrapContentSize(),
                                bitmap = bitmap.asImageBitmap(),
                                contentDescription = "picture",
                            )
                        }
                    }
                }
            }
        }
    )
}

private fun takePhoto(
    controller: LifecycleCameraController,
    onPhotoTaken: (Bitmap) -> Unit,
    context: Context,
) {
    controller.takePicture(
        ContextCompat.getMainExecutor(context),
        object : ImageCapture.OnImageCapturedCallback() {
            override fun onCaptureSuccess(image: ImageProxy) {
                super.onCaptureSuccess(image)

                val matrix = Matrix().apply {
                    postRotate(image.imageInfo.rotationDegrees.toFloat())

                }
                val rotatedBitmap = Bitmap.createBitmap(
                    image.toBitmap(),
                    0,
                    0,
                    image.width,
                    image.height,
                    matrix,
                    true
                )

                onPhotoTaken(rotatedBitmap)
                image.close()
            }

            override fun onError(exception: ImageCaptureException) {
                super.onError(exception)
                Log.e("Camera", "Couldn't take photo: ", exception)
            }
        }
    )
}