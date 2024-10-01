package com.example.newsapp.domain.usecase.news

import com.example.newsapp.domain.model.Article
import com.example.newsapp.domain.repository.NewsRepository

class Select_Article (
    private val newsRepository: NewsRepository
) {

    suspend operator fun invoke (url: String) : Article?{
         return newsRepository.select_Article( url = url)
    }
}