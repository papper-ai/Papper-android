package com.example.papper.features.auth.registration.view.name

import android.annotation.SuppressLint
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
import com.example.papper.features.auth.registration.presentation.RegistrationViewModel
import com.example.papper.features.auth.registration.view.ContinueBtn
import com.example.papper.features.common.components.PageProgressComponent
import com.example.papper.theme.dimens
import org.orbitmvi.orbit.compose.collectAsState

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun NameBasic(
    modifier: Modifier = Modifier,
    viewModel: RegistrationViewModel,
) {
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
            FillNameText()
            Spacer(modifier = Modifier.padding(top = MaterialTheme.dimens.gapBetweenComponents2))
            NameTextField(viewModel = viewModel)
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize(1f),
        contentAlignment = Alignment.BottomCenter
    ) {
        Column(
            modifier = modifier
        ) {
            PageProgressComponent(pageCount = 5, currentPage = 1)
            Spacer(modifier = Modifier.padding(bottom = MaterialTheme.dimens.bottomGap2))
            ContinueBtn(
                onClick = {
                    viewModel.toSurname()
                },
                viewModel = viewModel,
                isEnable = viewModel.nameStatus.value
            )
            Spacer(modifier = Modifier.padding(bottom = MaterialTheme.dimens.bottomGap3))
        }
    }
}