package com.example.newsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat

import com.example.newsapp.presentation.navGraph.NavGraph
import com.example.newsapp.ui.theme.NewsAppTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val mainViewModel  by viewModels<MainViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window,false)

        installSplashScreen().apply {
            setKeepOnScreenCondition{
                mainViewModel.splashCondition
            }
        }
        enableEdgeToEdge()
        setContent {
            NewsAppTheme {
//                val onBoardingViewModel : OnBoardingViewModel = hiltViewModel()
//                OnboardingScreen(event = {
//                    onBoardingViewModel.onEvent(it) /// we can also did the same by onBoardingViewModel::onEvent  => (::) this is basically for {}
//                }
//                )

                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ){
                    val startDestination = mainViewModel.startDestination
                    NavGraph(startDestination = startDestination)
                }
                            }
        }
    }
}

