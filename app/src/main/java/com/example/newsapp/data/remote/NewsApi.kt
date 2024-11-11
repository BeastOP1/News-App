package com.example.newsapp.data.remote

import com.example.newsapp.data.remote.dto.NewsResponse
import com.example.newsapp.domain.model.Source
import com.example.newsapp.ui.theme.utils.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface NewsApi {

    @GET("everything")
    suspend fun getNews(
        @Query("page") page: Int,
        @Query("sources") source: String,
        @Query("apiKey") apiKey : String =API_KEY
    ): NewsResponse


    @GET("everything")
    suspend fun searchNews(
        @Query("q") searchQuery: String,
        @Query("sources") source: String,
        @Query("apiKey") apiKey: String = API_KEY,
        @Query("page") page: Int
    ): NewsResponse

    @GET("top-headlines/")
    suspend fun categoryNews(
        @Query("page") page: Int,
        @Query("sources") source: String? = null,
        @Query("apiKey") apiKey: String =API_KEY,
        @Query("country") country : String? = null,
        @Query("category") category: String?= null,
    ): NewsResponse


    @GET("top-headlines")
    suspend fun headlineNews(
        @Query("page") page: Int,
        @Query("sources") source: String? = null,
        @Query("apiKey") apiKey: String = API_KEY,
        @Query("country") country: String,
        @Query("category") category: String? = null ,
    ): NewsResponse

}

 //1

//first we create interface our api call
// next we go to NewsRepository ,,
// by implementing paging3 we have to provide pagingSource ,
// so for that we just create a class NewsPagingSource in which two suspend override method getRefreshKey and load ... after creating this as before we created NewsRepository so now we have to apply Implementation so for that we created a class NewsRepositoryImpl
///lastly we create a class GeNews