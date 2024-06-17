package com.example.papper.features.storage.create_file.view.typing_title

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.papper.features.storage.create_file.presentation.CreateFileViewModel
import com.example.papper.theme.dimens

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun TitleBasic(
    modifier: Modifier = Modifier,
    viewModel: CreateFileViewModel,
) {
    Log.e("TAG", "TitleBasic: ", )

    Box(
        modifier = modifier
            .fillMaxSize(1f),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier
                .wrapContentSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.padding(top = MaterialTheme.dimens.gapBetweenComponents3))
            FillTitleText()
            Spacer(modifier = Modifier.padding(top = MaterialTheme.dimens.gapBetweenComponents2))
            TitleTextField(
                viewModel = viewModel
            )
        }
    }

//    Column(
//        modifier = Modifier
//            .fillMaxSize(1f),
//        verticalArrangement = Arrangement.Bottom,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Column(
//            modifier = Modifier
//        ) {
//            PageProgressComponent(pageCount = 3, currentPage = 1)
//            Spacer(modifier = Modifier.padding(bottom = MaterialTheme.dimens.bottomGap2))
//            ContinueBtn(
//                onClick = {viewModel.toAttachPhotos()},
//                isEnable = viewModel.collectAsState().value.title.isNotEmpty()
//            )
//            Spacer(modifier = Modifier.padding(bottom = MaterialTheme.dimens.bottomGap3))
//        }
//    }
}