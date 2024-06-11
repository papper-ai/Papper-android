package com.example.papper.features.storage.create_file.view.attach_photos

import android.graphics.BitmapFactory
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.core.app.ActivityOptionsCompat
import com.example.papper.R
import com.example.papper.features.common.components.StrokeButtonComponent
import com.example.papper.features.storage.create_file.presentation.CreateFileViewModel
import com.example.papper.theme.dimens
import com.example.papper.utils.GetFile.getFile

@Composable
fun AddPhotosBtn(
    modifier: Modifier = Modifier,
    viewModel: CreateFileViewModel,
) {
    val context = LocalContext.current
    val selectFilesActivity = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetMultipleContents()) { photoUris ->
        val tempList = mutableListOf<Uri>()
        photoUris.forEach { photoUri ->
            tempList.add(photoUri)
            val path = photoUri.getFile(context)?.path
            if (path != null) {
                viewModel.addPhoto(bitmap = BitmapFactory.decodeFile(path))
            }
        }
    }

    StrokeButtonComponent(
        modifier = modifier
            .padding(start = MaterialTheme.dimens.gapBetweenComponentScreen, end = MaterialTheme.dimens.buttonGap/2),
        onClick = {
            selectFilesActivity.launch("image/*", options = ActivityOptionsCompat.makeBasic())
        },
        text = stringResource(id = R.string.attach_photo),
    )
}