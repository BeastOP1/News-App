package com.example.newsapp.presentation.navGraph

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.newsapp.presentation.onBoarding.OnBoardingViewModel
import com.example.newsapp.presentation.news_navigator.NewsNavigator
import com.example.newsapp.presentation.onBoarding.components.OnboardingScreen


@Composable
fun NavGraph( startDestination: String) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {


        navigation(
            route = Route.AppStartNavigation.route,
             startDestination = Route.OnBoardingScreen.route
        ){
            composable(
                route = Route.OnBoardingScreen.route
            ){
                val onBoardingViewModel : OnBoardingViewModel = hiltViewModel()
                OnboardingScreen(
                    event = onBoardingViewModel::onEvent
                )
            }
        }

        navigation(
            route = Route.NewsNavigation.route,
            startDestination = Route.NewsNavigatorScreen.route
        ){
            composable(
                route = Route.NewsNavigatorScreen.route
            ){
                NewsNavigator()
            }
        }
    }
    
}