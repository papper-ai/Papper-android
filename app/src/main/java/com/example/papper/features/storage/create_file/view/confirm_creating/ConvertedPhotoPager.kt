package com.example.papper.features.storage.create_file.view.confirm_creating

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.util.lerp
import com.example.papper.features.common.components.CardItemComponent
import com.example.papper.features.common.components.PageProgressComponent
import com.example.papper.features.storage.create_file.model.PhotoModel
import com.example.papper.features.storage.create_file.presentation.CreateFileViewModel
import com.example.papper.features.storage.create_storage.view.attach_files.CreateStorageBtnStatus
import com.example.papper.theme.dimens
import kotlin.math.absoluteValue

@Composable
fun ConvertedPhotoPager(
    modifier: Modifier = Modifier,
    viewModel: CreateFileViewModel,
    list: List<PhotoModel>,
    onEmptyList: () -> Unit,
) {
    val pagerState = rememberPagerState(
        pageCount = {list.size}
    )

    Column(
        modifier = modifier
    ) {
        if (list.isNotEmpty()) {
            HorizontalPager(
                modifier = Modifier,
                state = pagerState,
                contentPadding = PaddingValues(horizontal = MaterialTheme.dimens.gapBetweenComponentScreen),
                pageSpacing = MaterialTheme.dimens.bottomGap,
            ) { page ->
                CardItemComponent(
                    modifier = Modifier
                        .graphicsLayer {
                            val pageOffset = (
                                    (pagerState.currentPage - page) + pagerState
                                        .currentPageOffsetFraction
                                    ).absoluteValue

                            alpha = lerp(
                                start = 0.5f,
                                stop = 1f,
                                fraction = 1f - pageOffset.coerceIn(0f, 1f)
                            )
                        },
                ) {
                    ConvertedTextPagerItem(
                        viewModel = viewModel,
                        convertedPhoto = list[page]
                    )
                }
            }
            Spacer(modifier = Modifier.height(MaterialTheme.dimens.bottomGap2))
            PageProgressComponent(
                modifier = Modifier,
                pageCount = if (pagerState.pageCount > 1) pagerState.pageCount else 0,
                currentPage = pagerState.currentPage + 1
            )
        } else {
            onEmptyList()
        }
    }
    if (pagerState.currentPage+1 == pagerState.pageCount)
        viewModel.createFileBtnStatus.value = CreateStorageBtnStatus(isLoading = false, isEnable = true)
    else
        viewModel.createFileBtnStatus.value = CreateStorageBtnStatus(isLoading = false, isEnable = false)
}
