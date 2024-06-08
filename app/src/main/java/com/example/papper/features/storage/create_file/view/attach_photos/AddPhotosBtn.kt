package com.example.papper.features.storage.create_file.view.attach_photos

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.papper.R
import com.example.papper.features.common.components.StrokeButtonComponent
import com.example.papper.features.storage.create_file.presentation.CreateFileViewModel
import com.example.papper.theme.dimens

@Composable
fun AddPhotosBtn(
    modifier: Modifier = Modifier,
    viewModel: CreateFileViewModel,
) {
    // TODO: добавить крепеж jpg, png и т.д.
//    val context = LocalContext.current
//    val mimeTypeFilter = arrayOf("application/pdf", "text/plain", "text/markdown", "application/vnd.openxmlformats-officedocument.wordprocessingml.document")
//
//    val selectedUris = remember { mutableStateOf<List<Uri>>(emptyList()) }
//    val selectFilesActivity = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetMultipleContents()) { fileUris ->
//        val tempList = mutableListOf<Uri>()
//        fileUris.forEach { fileUri ->
//            val fileType = context.contentResolver.getType(fileUri)
//            if (fileType in mimeTypeFilter) {
//                tempList.add(fileUri)
//                val file = fileUri.getFile(context)
//                if (file != null) {
//                    viewModel.addFile(file)
//                }
//            } else {
//                Toast.makeText(context, "${fileType} не поддерживается", Toast.LENGTH_SHORT).show()
//            }
//        }
//        selectedUris.value = tempList
//    }

    StrokeButtonComponent(
        modifier = modifier
            .padding(start = MaterialTheme.dimens.gapBetweenComponentScreen, end = 5.dp),
        onClick = {
            //selectFilesActivity.launch("*/*", options = ActivityOptionsCompat.makeBasic())
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