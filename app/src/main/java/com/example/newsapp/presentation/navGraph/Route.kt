package com.example.newsapp.presentation.navGraph

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.google.firebase.auth.FirebaseAuth

sealed class Route(val route: String) {


    object OnBoardingScreen: Route( route = "onBoardingScreen")
    object AuthScreen: Route(route = "authScreen")
    object HomeScreen: Route( route = "homeScreen")
    object ProfileScreen: Route(route = "profileScreen")
    object SearchScreen: Route( route = "searchScreen")
    object BookMarkScreen: Route( route = "bookScreen")
    object DetailScreen: Route( route = "detailScreen")

    ///when app start
    object AppStartNavigation: Route( route = "appStartNavigation")
    //after the onboard screen
    object NewsNavigation: Route( route = "newsNavigation")
    object NewsNavigatorScreen: Route( route = "newsNavigator")
}

object Router {
    val currentUser = FirebaseAuth.getInstance().currentUser
   var currentScreen  : MutableState<Route> = mutableStateOf(if (currentUser == null){
        Route.AuthScreen
    } else{ Route.HomeScreen})

    fun navigateTo(destination: Route) {
        currentScreen.value = destination
    }
}