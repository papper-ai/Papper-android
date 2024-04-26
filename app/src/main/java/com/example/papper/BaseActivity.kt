package com.example.papper

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.papper.navigation.AppNavigation
import com.example.papper.theme.PapperTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BaseActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            PapperTheme {
                Surface(
                   modifier = Modifier
                       .fillMaxSize(),
                   color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation(navHostController = navController)
                }
            }
        }
    }
}