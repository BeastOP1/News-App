package com.example.newsapp.data.repository

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.newsapp.data.local.NewsDao
import com.example.newsapp.data.remote.NewsApi
import com.example.newsapp.data.remote.NewsPagingSource
import com.example.newsapp.data.remote.SearchNewsPagingSource
import com.example.newsapp.domain.model.Article
import com.example.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import androidx.paging.Pager as Pager1

class NewsRepositoryImpl (
    private val newsApi: NewsApi,
    private val newsDao: NewsDao
): NewsRepository{
    override fun getNews(sources: List<String>): Flow<PagingData<Article>> {
      return Pager1(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                NewsPagingSource(
                    newsApi = newsApi,
                    sources = sources.joinToString(separator = ",")
                )
            }
        ).flow
    }

    override fun searchNews(searchQuery: String, sources: List<String>): Flow<PagingData<Article>> {
     return Pager1(
         config = PagingConfig(pageSize = 10),
         pagingSourceFactory = {
             SearchNewsPagingSource(
                 searchQuery = searchQuery,
                 newsApi = newsApi,
                 source = sources.joinToString(separator = ",")
             )
         }
     ).flow
    }

    override suspend fun insertArticle(article: Article) {
        newsDao.insertArticle(article)
    }

    override suspend fun deleteArticle(article: Article) {
        newsDao.deleteArticle(article)
    }

    override fun selectArticles(): Flow<List<Article>> {
        return newsDao.getArticles()
    }

    override suspend fun select_Article(url: String): Article? {
        return newsDao.getArticle(url)
    }
}

//4