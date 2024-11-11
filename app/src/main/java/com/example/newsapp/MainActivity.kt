package com.example.newsapp

import android.os.Bundle
import android.graphics.Color
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat

import com.example.newsapp.presentation.navGraph.NavGraph
import com.example.newsapp.ui.theme.NewsAppTheme
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window,false)
        window.statusBarColor= Color.TRANSPARENT

        val mainViewModel by viewModels<MainViewModel>()
        installSplashScreen().apply {
            setKeepOnScreenCondition{
                mainViewModel.splashCondition
            }
        }
        enableEdgeToEdge()
        setContent {
            NewsAppTheme {
                val startDestination = mainViewModel.startDestination

                LaunchedEffect(mainViewModel.splashCondition) {
                    if (!mainViewModel.splashCondition) {
                        delay(200)  // Optional delay to improve UI transition
                    }
                }

                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    NavGraph(startDestination = startDestination)
                }
            }
        }
    }
}

