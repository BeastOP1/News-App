package com.example.newsapp.data.remote

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.newsapp.domain.model.Article

class HeadlineNewsPagingSource(
    private val newsApi: NewsApi,
    private val country: String
): PagingSource<Int,Article>() {
    private var totalNewsCount = 0

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage  = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1)?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {

        val page = params.key ?:1
        return try {
            val newsResponse = newsApi.headlineNews(
                page = page,
                country = country
            )
            Log.d("News","Headline News Fetch Successfully ${newsResponse.articles.size} Articles")

            totalNewsCount += newsResponse.articles.size

            val articles = newsResponse.articles.distinctBy { it.urlToImage }

            LoadResult.Page(
                data = articles,
                nextKey = if(totalNewsCount == newsResponse.totalResults) null else page +1,
                prevKey = null
            )

        }catch (e: Exception){
            e.printStackTrace()
            LoadResult.Error(throwable = e)
        }
    }
}