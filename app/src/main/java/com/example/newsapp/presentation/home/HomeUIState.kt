package com.example.newsapp.presentation.home

import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import com.example.newsapp.domain.model.Article
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class HomeUIState(
    val category: String = "general",
//    val country : String = "us",
    val articles: Flow<PagingData<Article>> = emptyFlow(),
//    val headArticle: Flow<PagingData<Article>> = emptyFlow()
)