package com.aldikitta.jetmvvmmovie.ui.screens.acitivity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.aldikitta.jetmvvmmovie.ui.screens.mainscreen.MainScreen
import com.aldikitta.jetmvvmmovie.ui.theme.JetMVVMMovieTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val splashViewModel: MainActivityViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                splashViewModel.isLoading.value
            }
        }
        setContent {
            JetMVVMMovieTheme {
                MainScreen()
            }
        }
    }
}
