package com.example.papper.features.storage.create_file

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import com.example.papper.features.common.components.PageProgressComponent
import com.example.papper.features.storage.create_file.presentation.CreateFileViewModel
import com.example.papper.features.storage.create_file.view.ContinueBtn
import com.example.papper.features.storage.create_file.view.TopBar
import com.example.papper.features.storage.create_file.view.attach_photos.AttachPhotoBasic
import com.example.papper.features.storage.create_file.view.attach_photos.ConvertPhotosBtn
import com.example.papper.features.storage.create_file.view.confirm_creating.ConfirmBtn
import com.example.papper.features.storage.create_file.view.confirm_creating.ConfirmCreatingBasic
import com.example.papper.features.storage.create_file.view.typing_title.TitleBasic
import com.example.papper.theme.dimens
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun CreateFileBasic(
    modifier: Modifier = Modifier,
    viewModel: CreateFileViewModel,
    //pagerState: PagerState,
) {
    val coroutineScope = rememberCoroutineScope()

    val pagerState = rememberPagerState(
        pageCount = {3}
    )
    val focusManager = LocalFocusManager.current

    Scaffold (
        topBar = {
            TopBar(
                viewModel = viewModel,
                //currentPage = pagerState.currentPage
                pagerState = pagerState,
            )
        },
        content = {
            Column(
                modifier = modifier
                    .padding(it)
            ) {
                HorizontalPager(
                    modifier = Modifier
                        .weight(1f),
                    state = pagerState,
                    userScrollEnabled = false,
                ) { currentPage ->
                    when (currentPage) {
                        0 -> { TitleBasic (viewModel = viewModel) }
                        1 -> {
                            AttachPhotoBasic (
                                viewModel = viewModel,
                                onBackHandler = {
                                    coroutineScope.launch {
                                        pagerState.animateScrollToPage(0)
                                    }
                                }
                            )
                        }
                        2 -> { ConfirmCreatingBasic(viewModel = viewModel, pagerState = pagerState) }
                        3 -> {}
                    }
                }

                Column(
                    modifier = Modifier,
                    verticalArrangement = Arrangement.Bottom,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Column(
                        modifier = Modifier
                    ) {
                        PageProgressComponent(pageCount = pagerState.pageCount, currentPage = pagerState.currentPage + 1)
                        Spacer(modifier = Modifier.padding(bottom = MaterialTheme.dimens.bottomGap2))
                        when (pagerState.currentPage) {
                            0 -> {
                                ContinueBtn(
                                    onClick = {
                                        focusManager.clearFocus()
                                        coroutineScope.launch {
                                            pagerState.animateScrollToPage(1)
                                        }
                                    },
                                    isEnable = viewModel.collectAsState().value.title.isNotEmpty()
                                )
                            }
                            1 -> {
                                ConvertPhotosBtn(
                                    onClick = {
                                        viewModel.convertPhotos().invokeOnCompletion {
                                            coroutineScope.launch {
                                                pagerState.animateScrollToPage(2)
                                            }
                                        }
                                    },
                                    viewModel = viewModel
                                )
                            }
                            2 -> {
                                ConfirmBtn(
                                    onClick = { viewModel.navigatePopBack() },
                                    isEnable = viewModel.createFileBtnStatus.value.isEnable,
                                )
                            }
                            3 -> {

                            }
                        }
                        Spacer(modifier = Modifier.padding(bottom = MaterialTheme.dimens.bottomGap3))
                    }
                }
            }
        },
    )
}