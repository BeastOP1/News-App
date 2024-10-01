package com.example.newsapp.presentation.news_navigator

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmarks
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.newsapp.domain.model.Article
import com.example.newsapp.presentation.bookmark.BookMarkScreen
import com.example.newsapp.presentation.bookmark.BookmarkViewModel
import com.example.newsapp.presentation.detail.DetailEvent
import com.example.newsapp.presentation.detail.DetailScreen
import com.example.newsapp.presentation.detail.DetailViewModel
import com.example.newsapp.presentation.home.HomeScreen
import com.example.newsapp.presentation.home.HomeViewModel
import com.example.newsapp.presentation.navGraph.Route
import com.example.newsapp.presentation.news_navigator.components.BottomNavigationItem
import com.example.newsapp.presentation.news_navigator.components.NewsBottomNavigation
import com.example.newsapp.presentation.search.SearchScreen
import com.example.newsapp.presentation.search.SearchViewModel

@Composable
fun NewsNavigator() {

    val bottomNavigationItems = remember {
            listOf(
                BottomNavigationItem(icon = Icons.Filled.Home, text = "Home"),
                BottomNavigationItem(icon = Icons.Filled.Search, text = "Search"),
                BottomNavigationItem(icon = Icons.Filled.Bookmarks, text = "Bookmark")
            )
    }

    val navController = rememberNavController()

    val backstackState  = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable {
        mutableStateOf(0)
    }


    selectedItem =   remember(key1 = backstackState) {
        when(backstackState?.destination?.route){

            Route.HomeScreen.route -> 0
            Route.SearchScreen.route -> 1
            Route.BookMarkScreen.route -> 2
            else -> 0
        }

    }

    val isBottomBarVisible = remember(key1 = backstackState) {
        backstackState?.destination?.route == Route.HomeScreen.route ||
                backstackState?.destination?.route == Route.SearchScreen.route ||
                backstackState?.destination?.route == Route.BookMarkScreen.route
        
    }


    Scaffold(


        bottomBar = {
            if(isBottomBarVisible){


            NewsBottomNavigation(
                items = bottomNavigationItems,
                selected = selectedItem,
                onItemClick = {index->
                    when(index){

                        0 -> navigateToTab(
                            navController = navController,
                            route = Route.HomeScreen.route
                        )
                        1 -> navigateToTab(
                            navController = navController,
                            route = Route.SearchScreen.route
                        )
                        2-> navigateToTab(
                            navController = navController,
                            route = Route.BookMarkScreen.route
                        )
                    }
                }
            )
            }
        }
    ) {
        val bottomPadding  = it.calculateBottomPadding()
        NavHost(navController =navController , startDestination = Route.HomeScreen.route, modifier = Modifier.padding(bottom =  bottomPadding)) {

            composable(route = Route.HomeScreen.route){
                val viewModel : HomeViewModel = hiltViewModel()
                val articles =viewModel.news.collectAsLazyPagingItems()
                
                HomeScreen(articles = articles,
                    navigateToSearch = {

                        navigateToTab(
                            navController = navController,
                            route = Route.SearchScreen.route
                        )
                },
                    navigateToDetail = {article ->
                        navigateTODetail(navController = navController , article = article)
                    }
                )
            }
            composable(route = Route.SearchScreen.route){
                val viewModel: SearchViewModel = hiltViewModel()

                val state =viewModel.state.value
                
                SearchScreen(state = state, event = viewModel::onEvent, navigateToDetails = {
                    navigateTODetail(navController = navController , article = it)

                })
            }

            composable(route = Route.DetailScreen.route){
                val viewModel: DetailViewModel = hiltViewModel()
                //TODo: handle side effects

                val sideEffect by viewModel.sideEffect.collectAsState()

                LaunchedEffect(sideEffect) {
                    sideEffect?.let {
//                        Toast.makeText(LocalContext.current, it, Toast.LENGTH_SHORT).show()
                        viewModel.omEvent(DetailEvent.RemoveSideEffect)
                    }
                }

//                if(viewModel.sideEffect != null){
//                    Toast.makeText(LocalContext.current,viewModel.sideEffect,Toast.LENGTH_SHORT).show()
//                    viewModel.omEvent(DetailEvent.RemoveSideEffect)
//                }
                navController.previousBackStackEntry?.savedStateHandle?.get<Article?>("article")?.let { article ->
                    
                    DetailScreen(article = article, event = viewModel::omEvent, navigateUp = {
                        navController.navigateUp()
                    })
                }
            }
            composable(route = Route.BookMarkScreen.route){
                val viewModel : BookmarkViewModel = hiltViewModel()

                val state = viewModel.state.value
                BookMarkScreen(state = state , navigateToDetails = {article ->
                    navigateTODetail(navController = navController,
                        article =article
                        )
                })
            }
        }

    }
}



private fun navigateToTab(navController : NavController , route : String){
    navController.navigate(route){
        navController.graph.startDestinationRoute?.let {homeScreen ->
            popUpTo(homeScreen){
                saveState = true
            }
            restoreState = true
            launchSingleTop = true
        }
    }
}

private fun navigateTODetail(navController: NavController,article: Article){

    navController.currentBackStackEntry?.savedStateHandle?.set("article", article)
    navController.navigate(
        route = Route.DetailScreen.route
    )
}