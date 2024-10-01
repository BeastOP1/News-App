package com.example.newsapp.presentation.bookmark

import com.example.newsapp.domain.model.Article

data class BookmarkState(
    val articles: List<Article> = emptyList(),
    val bookmarkedArticles: Set<Article> = emptySet()  // To store bookmarked art
)
