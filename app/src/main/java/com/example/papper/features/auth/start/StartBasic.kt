package com.example.papper.features.auth.start

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.example.papper.features.auth.start.view.CompanyPolicyClickableText
import com.example.papper.features.auth.start.view.SignInBtn
import com.example.papper.features.auth.start.view.SignUpBtn
import com.example.papper.features.common.components.BigLogoComponent
import com.example.papper.theme.dimens

@Composable
fun StartBasic(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column (
            modifier = modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom,
        ) {
            BigLogoComponent(modifier = Modifier.weight(1f))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom,
            ) {
                CompanyPolicyClickableText(modifier = modifier)
                Spacer(modifier = Modifier.padding(MaterialTheme.dimens.bottomGap))
                SignInBtn(navHostController = navHostController)
                Spacer(modifier = Modifier.padding(MaterialTheme.dimens.bottomGap))
                SignUpBtn(navHostController = navHostController)
                Spacer(modifier = Modifier.padding(MaterialTheme.dimens.bottomGap2))
            }
        }
    }
}