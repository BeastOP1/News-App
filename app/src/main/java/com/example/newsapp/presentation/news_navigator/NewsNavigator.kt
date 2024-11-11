package com.example.newsapp.presentation.news_navigator

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.newsapp.R
import com.example.newsapp.domain.model.Article
import com.example.newsapp.presentation.auth.AuthScreen
import com.example.newsapp.presentation.auth.AuthViewModel
import com.example.newsapp.presentation.auth.UIEvent
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
import com.example.newsapp.presentation.profile.ProfileScreen
import com.example.newsapp.presentation.profile.ProfileViewModel
import com.example.newsapp.presentation.search.SearchScreen
import com.example.newsapp.presentation.search.SearchViewModel
import com.example.newsapp.ui.theme.CustomDark
import com.example.newsapp.ui.theme.CustomLight
import com.google.firebase.auth.FirebaseAuth

@Composable
fun NewsNavigator() {

    val bottomNavigationItems = remember {
        listOf(
            BottomNavigationItem(icon = R.drawable.home, text = "Home"),
            BottomNavigationItem(icon = R.drawable.person, text = "Profile"),
            BottomNavigationItem(icon = R.drawable.bookmark, text = "Bookmark")
        )
    }

    val navController = rememberNavController()

    val backstackState = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable {
        mutableStateOf(0)
    }

    val currentUser = FirebaseAuth.getInstance().currentUser?.uid
    var startDestination =
        if (currentUser != null) Route.HomeScreen.route else Route.AuthScreen.route

    selectedItem = remember(key1 = backstackState) {
        when (backstackState?.destination?.route) {

            Route.HomeScreen.route -> 0
            Route.ProfileScreen.route -> 1
            Route.BookMarkScreen.route -> 2
            else -> 0
        }

    }

    val isVisible = if (startDestination == Route.HomeScreen.route) true else false




    Scaffold(
        modifier = Modifier.background(
            if (isSystemInDarkTheme()) CustomDark else CustomLight
        ),
        containerColor = Color.Transparent,
        bottomBar = {

            if (isVisible) {

                NewsBottomNavigation(
                    items = bottomNavigationItems,
                    selected = selectedItem,
                    onItemClick = { index ->
                        when (index) {

                            0 -> navigateToTab(
                                navController = navController,
                                route = Route.HomeScreen.route
                            )

                            1 -> navigateToTab(
                                navController = navController,
                                route = Route.ProfileScreen.route
                            )

                            2 -> navigateToTab(
                                navController = navController,
                                route = Route.BookMarkScreen.route
                            )
                        }
                    }
                )

            }

        },

        ) {
        val bottomPadding = it.calculateBottomPadding()
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = Modifier.padding(bottom = bottomPadding)
        ) {

            composable(route = Route.HomeScreen.route) {
                val viewModel: HomeViewModel = hiltViewModel()
                val headlineArticles: LazyPagingItems<Article> =
                    viewModel.headlineNews.collectAsLazyPagingItems()


                HomeScreen(
                    state = viewModel.state,
                    onEvent = viewModel::onEvent,
                    navigateToDetail = { article ->
                        navigateTODetail(navController = navController, article = article)
                    },
                    navigateToSearch = {
                        navController.navigate(route = Route.SearchScreen.route)
                    },
                    headArticles = headlineArticles
                )
            }

            composable(route = Route.SearchScreen.route) {
                val viewModel: SearchViewModel = hiltViewModel()

                val state = viewModel.state.value

                SearchScreen(
                    state = state,
                    event = viewModel::onEvent,
                    navController = navController,
                    navigateToDetails = {
                        navigateTODetail(navController = navController, article = it)
                    })
            }

            composable(route = Route.DetailScreen.route) {
                val viewModel: DetailViewModel = hiltViewModel()
                //TODo: handle side effects

                val sideEffect by viewModel.sideEffect.collectAsState()
                val context = LocalContext.current
                LaunchedEffect(key1 = sideEffect) {

                    sideEffect?.let { message ->

                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                        viewModel.omEvent(DetailEvent.RemoveSideEffect)
                    }
                }

                navController.previousBackStackEntry?.savedStateHandle?.get<Article?>("article")
                    ?.let { article ->

                        DetailScreen(article = article, event = viewModel::omEvent, navigateUp = {
                            navController.navigateUp()
                        })
                    }
            }
            composable(route = Route.BookMarkScreen.route) {
                val viewModel: BookmarkViewModel = hiltViewModel()

                val state = viewModel.state.value
                BookMarkScreen(state = state, navigateToDetails = { article ->
                    navigateTODetail(
                        navController = navController,
                        article = article
                    )
                })
            }
            composable(route = Route.AuthScreen.route) {
                val authViewModel: AuthViewModel = hiltViewModel()
                val uiEvent by authViewModel.uiEvent.collectAsState()

                LaunchedEffect(uiEvent) {
                    when (uiEvent) {
                        is UIEvent.NavigateToHome -> {
                            navController
                                .navigate(Route.HomeScreen.route)
                        }

                        is UIEvent.ShowError -> {
//                Toast.makeText(.current, uiEvent.message, Toast.LENGTH_SHORT).show()
                        }

                        UIEvent.None -> {
                            null
                        }

                        UIEvent.NavigateToLog -> {
                            navController.navigate(Route.AuthScreen.route)
                        }
                    }
                }
                AuthScreen(
                    authUIState = authViewModel.authState,
                    navController = navController,
                    event = {
                        authViewModel.onEvent(it)
                    }
                )
            }

            composable(route = Route.ProfileScreen.route) {
                val profileViewModel: ProfileViewModel = hiltViewModel()
                val profileUIState = profileViewModel.profileUiState.value
                val user: String? = FirebaseAuth.getInstance().currentUser?.email
                ProfileScreen(user = user.toString(), profileUIState, event = {
                    profileViewModel.onEvent(it)
                },
                    logOut = {
                        profileViewModel.logOut()
                        navController.navigate(Route.AuthScreen.route)
                    }
                )


            }
        }

    }
}


private fun navigateToTab(navController: NavController, route: String) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let { homeScreen ->
            popUpTo(homeScreen) {
                saveState = true
            }
            restoreState = true
            launchSingleTop = true
        }
    }
}

private fun navigateTODetail(navController: NavController, article: Article) {

    navController.currentBackStackEntry?.savedStateHandle?.set("article", article)
    navController.navigate(
        route = Route.DetailScreen.route
    )
}