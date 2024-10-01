package com.example.newsapp.presentation.search

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.newsapp.domain.model.Article
import com.example.newsapp.presentation.common.ArticleList
import com.example.newsapp.presentation.common.SearchBar
import com.example.newsapp.presentation.navGraph.Route
import com.example.newsapp.presentation.onBoarding.components.Dimens.MediumPadding1

@Composable
fun SearchScreen(
    state:SearchState,
    event : (SearchEvent) -> Unit,
    navigateToDetails : (Article) -> Unit

) {

    Column(
        modifier = Modifier
            .fillMaxSize().background(if(isSystemInDarkTheme()) Color.Black else Color.White)
            .padding(
                top = MediumPadding1,
                end = MediumPadding1,
                start = MediumPadding1
            )
            .statusBarsPadding()
    ) {

        SearchBar(
            text = state.searchQuery,
            readOnly = false,
            onValueChange ={
                event(SearchEvent.UpdateSearchQuery(it))
            },
             onSearch = {
                 event(SearchEvent.SearchNews)
             }

        )
        
        Spacer(modifier = Modifier.height(MediumPadding1))


        state.articles?.let {
            val articles = it.collectAsLazyPagingItems()
            ArticleList(articles = articles , onClick = {
                navigateToDetails(it)
            })
        }
    }
}