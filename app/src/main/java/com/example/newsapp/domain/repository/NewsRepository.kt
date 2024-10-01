package com.example.newsapp.domain.repository

import androidx.paging.PagingData
import com.example.newsapp.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    fun getNews(sources: List<String>) : Flow<PagingData<Article>>
    fun searchNews(searchQuery:String,sources: List<String>) : Flow<PagingData<Article>>

    suspend fun insertArticle(article: Article)

    suspend fun deleteArticle(article: Article)

    fun selectArticles() :Flow<List<Article>>
    suspend fun select_Article(url : String): Article?

}



//2

///in this case we ill load millions of data from the server so it take long time to fetch all data so we ill justify  how many articles we wanna load from the api and then api will respond that
///that actually helps us to get faster responses
//paging is basically a technique that enables you to fetch data by small chunks from the server

//by implementing pagging3
//we are going to create a remote paging source