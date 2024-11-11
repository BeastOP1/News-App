package com.example.newsapp.domain.usecase.news

import androidx.paging.PagingData
import com.example.newsapp.domain.model.Article
import com.example.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetCategoryNews(
    private val newsRepository: NewsRepository
) {
    operator fun  invoke(category: String, country: String): Flow<PagingData<Article>>{
        return newsRepository.categoryNews(
            category = category,
            country = country
        )


    }
}