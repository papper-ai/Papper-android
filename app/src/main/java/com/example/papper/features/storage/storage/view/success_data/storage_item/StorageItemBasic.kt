package com.example.papper.features.storage.storage.view.success_data.storage_item

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.papper.R
import com.example.papper.features.storage.storage.model.FilePresentationModel
import com.example.papper.features.storage.storage.presentation.StorageViewModel
import com.example.papper.theme.Heading1
import com.example.papper.theme.Heading2
import com.example.papper.theme.dimens
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StorageItemBasic(
    modifier: Modifier = Modifier,
    viewModel: StorageViewModel,
    file: FilePresentationModel,
) {
    val scope = rememberCoroutineScope()
    var isBottomSheetVisible by rememberSaveable { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    BottomSheetContent(
        isBottomSheetVisible = isBottomSheetVisible,
        sheetState = sheetState,
        onDismiss = {
            scope.launch { sheetState.hide() }
                .invokeOnCompletion { isBottomSheetVisible = false }
        },
        file = file,
    )

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(MaterialTheme.dimens.buttonsHeight)
            .padding(
                start = MaterialTheme.dimens.gapBetweenComponentScreen * 2,
                end = MaterialTheme.dimens.gapBetweenComponentScreen * 2,
            )
            .background(
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
                shape = RoundedCornerShape(MaterialTheme.dimens.buttonCornerRadius),
            )
            .padding(
                start = MaterialTheme.dimens.gapBetweenComponentScreen,
                end = MaterialTheme.dimens.gapBetweenComponentScreen,
            )
            .clickable {
                scope.launch {
                    isBottomSheetVisible = true
                    sheetState.expand()
                }
            },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            modifier = modifier
                .weight(1f),
            text = file.title,
            style = MaterialTheme.typography.Heading2,
            color = MaterialTheme.colorScheme.primary,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
        DeleteBtn(
            modifier = modifier
                .weight(0.2f),
            viewModel = viewModel,
            file = file,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetContent(
    isBottomSheetVisible: Boolean,
    sheetState: SheetState,
    onDismiss: () -> Unit,
    file: FilePresentationModel,
) {
    if (isBottomSheetVisible) {
        ModalBottomSheet(
            onDismissRequest = onDismiss,
            sheetState = sheetState,
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            shape = RoundedCornerShape(MaterialTheme.dimens.cardCornerRadius),
            dragHandle = null,
            windowInsets = WindowInsets(0, 0, 0, 0)
        ) {
            Box(
                modifier = Modifier
                    .statusBarsPadding()
                    .fillMaxWidth()
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                FilledIconButton(
                    modifier = Modifier.size(48.dp),
                    onClick = onDismiss,
                    colors = IconButtonDefaults.filledIconButtonColors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer
                    )
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Close,
                        contentDescription = "Hide the dialog.",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }

            LazyColumn(
                modifier = Modifier
                    .navigationBarsPadding()
                    .clip(
                        shape = RoundedCornerShape(
                            topStart = MaterialTheme.dimens.cardCornerRadius,
                            topEnd = MaterialTheme.dimens.cardCornerRadius
                        )
                    )
                    .background(color = MaterialTheme.colorScheme.onBackground)
                    .fillMaxWidth()
                    .padding(
                        start = MaterialTheme.dimens.gapBetweenComponentScreen,
                        end = MaterialTheme.dimens.gapBetweenComponentScreen,
                        top = 0.dp,
                    )
            ) {
                item {
                    Spacer(modifier = Modifier.height(MaterialTheme.dimens.bottomGap2))
                    Text(
                        text = file.title,
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.Heading1
                    )
                    Spacer(modifier = Modifier.height(MaterialTheme.dimens.bottomGap))
                    Text(
                        text = if (file.text != "") {
                            file.text
                        } else {
                            stringResource(id = R.string.empty_file)
                        },
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.Heading2
                    )


                    Spacer(modifier = Modifier.height(MaterialTheme.dimens.bottomGap2))
                }
            }
        }
    }
}