package com.example.papper.features.storage.create_storage.view.attach_files

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.core.app.ActivityOptionsCompat
import com.example.papper.R
import com.example.papper.features.common.components.StrokeButtonComponent
import com.example.papper.features.storage.create_storage.presentation.CreateStorageViewModel
import com.example.papper.utils.GetFile.getFile

@Composable
fun AddFilesBtn(
    modifier: Modifier = Modifier,
    viewModel: CreateStorageViewModel,
) {
    val context = LocalContext.current
    val mimeTypeFilter = arrayOf("application/pdf", "text/plain", "text/markdown", "application/vnd.openxmlformats-officedocument.wordprocessingml.document")

    val selectedUris = remember { mutableStateOf<List<Uri>>(emptyList()) }
    val selectFilesActivity = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetMultipleContents()) { fileUris ->
        val tempList = mutableListOf<Uri>()
        fileUris.forEach { fileUri ->
            val fileType = context.contentResolver.getType(fileUri)
            if (fileType in mimeTypeFilter) {
                tempList.add(fileUri)
                val file = fileUri.getFile(context)
                if (file != null) {
                    viewModel.addFile(file)
                }
            } else {
                Toast.makeText(context, "${fileType} не поддерживается", Toast.LENGTH_SHORT).show()
            }
        }
        selectedUris.value = tempList
    }

    StrokeButtonComponent(
        modifier = modifier,
        onClick = {
            selectFilesActivity.launch("*/*", options = ActivityOptionsCompat.makeBasic())
        },
        text = stringResource(id = R.string.attach_file),
    )
}



//@Composable
//fun AddFilesBtn(
//    modifier: Modifier = Modifier,
//    viewModel: CreateStorageViewModel,
//) {
//    val coroutineScope = rememberCoroutineScope()
//    val context = LocalContext.current
//    val mimeTypeFilter = arrayOf("application/pdf")
//
//    val selectedPdfUris = remember { mutableStateOf<List<Uri>>(emptyList()) }
//    val selectPdfActivity = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetMultipleContents()) { fileUris ->
//        selectedPdfUris.value = fileUris
//        fileUris.forEach { fileUri ->
//            val file = fileUri.getFile(context)
//            if (file != null) {
//                viewModel.addFile(file)
//            }
//        }
//    }
//
//    StrokeButtonComponent(
//        modifier = modifier,
//        onClick = {
//            coroutineScope.launch(AppDispatchers.io) {
//                selectPdfActivity.launch(mimeTypeFilter.first(), options = ActivityOptionsCompat.makeBasic())
//            }
//        },
//        text = stringResource(id = R.string.attach_file),
//    )
//}