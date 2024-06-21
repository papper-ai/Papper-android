package com.example.papper.features.storage.create_file

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.navigation.NavHostController
import com.example.papper.features.common.components.PageProgressComponent
import com.example.papper.features.storage.create_file.presentation.CreateFileScreenState
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

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CreateFileBasic(
    modifier: Modifier = Modifier,
    viewModel: CreateFileViewModel,
    navHostController: NavHostController,
) {
    val pagerState = rememberPagerState(
        pageCount = {3}
    )

    val coroutineScope = rememberCoroutineScope()

    when (viewModel.createFileScreenState.value) {
        CreateFileScreenState.TypingTitle -> {
            coroutineScope.launch {
                // Call scroll to on pagerState
                pagerState.animateScrollToPage(
                    0
                )
            }
        }
        CreateFileScreenState.AttachPhotos -> {
            coroutineScope.launch {
                // Call scroll to on pagerState
                pagerState.animateScrollToPage(
                    1
                )
            }
        }
        CreateFileScreenState.ConfirmCreating -> {
            coroutineScope.launch {
                // Call scroll to on pagerState
                pagerState.animateScrollToPage(
                    2
                )
            }
        }
        CreateFileScreenState.Error -> {
            coroutineScope.launch {
                // Call scroll to on pagerState
                pagerState.animateScrollToPage(
                    3
                )
            }
        }
    }

    Scaffold (
        topBar = {
            TopBar(viewModel = viewModel, navHostController = navHostController)
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
                        1 -> { AttachPhotoBasic (viewModel = viewModel) }
                        2 -> { ConfirmCreatingBasic(viewModel = viewModel) }
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
                                        viewModel.toAttachPhotos()
                                    },
                                    isEnable = viewModel.collectAsState().value.title.isNotEmpty()
                                )
                            }
                            1 -> {
                                ConvertPhotosBtn(
                                    onClick = {
                                        viewModel.convertPhotos()
                                    },
                                    viewModel = viewModel
                                )
                            }
                            2 -> {
                                ConfirmBtn(
                                    onClick = { /*TODO*/ },
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