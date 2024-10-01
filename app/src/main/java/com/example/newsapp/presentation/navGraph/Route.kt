package com.example.newsapp.presentation.navGraph

sealed class Route(val route: String) {


    object OnBoardingScreen: Route( route = "onBoardingScreen")
    object HomeScreen: Route( route = "homeScreen")
    object SearchScreen: Route( route = "searchScreen")
    object BookMarkScreen: Route( route = "bookScreen")
    object DetailScreen: Route( route = "detailScreen")
    object AppStartNavigation: Route( route = "appStartNavigation")
    object NewsNavigation: Route( route = "newsNavigation")
    object NewsNavigatorScreen: Route( route = "newsNavigator")
}