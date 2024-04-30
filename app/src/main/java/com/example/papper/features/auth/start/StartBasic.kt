package com.example.papper.features.auth.start

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.papper.features.auth.start.presentation.StartScreenState
import com.example.papper.features.auth.start.presentation.StartViewModel
import com.example.papper.features.auth.start.view.SignInBtn
import com.example.papper.features.auth.start.view.SignUpBtn
import com.example.papper.features.common.components.BigLogoComponent
import com.example.papper.features.common.components.ProgressBarComponent
import com.example.papper.theme.dimens
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun StartBasic(
    modifier: Modifier = Modifier,
    viewModel: StartViewModel,
    navHostController: NavHostController,
) {
    val coroutineScope = rememberCoroutineScope()
    val startLogoAnimation = remember {
        mutableStateOf(false)
    }
    val startAnimation = remember {
        mutableStateOf(false)
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        when (viewModel.startScreenState.value) {
            StartScreenState.Loading -> {
                coroutineScope.launch {
                    delay(1)
                    startLogoAnimation.value = true
                }
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        modifier = modifier,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        AnimatedVisibility(
                            visible = startLogoAnimation.value,
                            enter = fadeIn(tween(500)) + slideInVertically(tween(700)),
                            exit = fadeOut(tween(500)),
                        ) {
                            BigLogoComponent()
                        }
                        AnimatedVisibility(
                            visible = viewModel.showStartAnimation.value,
                            enter = fadeIn(tween(500)) + slideInVertically(
                                tween(700),
                                initialOffsetY = { it / 2 }),
                            exit = fadeOut(tween(500)),
                        ) {
                            Spacer(modifier = Modifier.padding(bottom = MaterialTheme.dimens.bottomGap2))
                            ProgressBarComponent()
                        }
                    }
                }
            }

            StartScreenState.Default -> {
                coroutineScope.launch {
                    delay(1)
                    startAnimation.value = true
                }
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AnimatedVisibility(
                        visible = startAnimation.value,
                        //enter = fadeIn(tween(500)),
                        exit = fadeOut(tween(500)),
                    ) {
                        Column(
                            modifier = modifier
                                .fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                        ) {
                            BigLogoComponent()
                            Spacer(modifier = Modifier.padding(bottom = 50.dp))
                        }
                    }

                    AnimatedVisibility(
                        visible = startAnimation.value,
                        enter = fadeIn(tween(500)) + slideInVertically(
                            tween(500),
                            initialOffsetY = { it / 2 }),
                        exit = fadeOut(tween(500)),
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Bottom,
                        ) {
                            //CompanyPolicyClickableText(modifier = modifier)
                            //Spacer(modifier = Modifier.padding(MaterialTheme.dimens.bottomGap))
                            SignInBtn(navHostController = navHostController)
                            Spacer(modifier = Modifier.padding(MaterialTheme.dimens.bottomGap))
                            SignUpBtn(navHostController = navHostController)
                            Spacer(modifier = Modifier.padding(MaterialTheme.dimens.bottomGap2))
                        }
                    }
                }
            }
        }
    }
}