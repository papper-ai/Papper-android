package com.example.papper.features.storage.storages.view.success_data.storage_item

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.papper.features.storage.storages.model.PresentationStoragePreviewModel
import com.example.papper.navigation.Screens
import com.example.papper.theme.dimens

@Composable
fun ItemBasic(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    storage: PresentationStoragePreviewModel,
) {
    Row(
        modifier = modifier
            .clickable {
                navHostController.navigate("${Screens.StorageScreen.route}/${storage.id}")
            }
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(
                start = MaterialTheme.dimens.gapBetweenComponentScreen,
                end = MaterialTheme.dimens.gapBetweenComponentScreen,
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
    ) {
        StorageIcon()
        Column(
            modifier = modifier
                .padding(start = MaterialTheme.dimens.gapBetweenComponents),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start,
        ) {
            TitleText(title = storage.title)
        }
    }
}

//@Preview
//@Composable
//fun ItemBasicPreview() {
//    ItemBasic(
//        modifier = Modifier,
//        storage = StorageDescription(
//            id = "1",
//            title = "Storage titletitletitletitletitletitletitle",
//        )
//    )
//}