package com.example.newsapp

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.newsapp.data.remote.NewsPagingSource
import com.example.newsapp.domain.model.Article
import kotlinx.coroutines.flow.Flow

//Paging is a jetpack library use for these
//Large Lists: Efficiently loading and displaying large lists of data from APIs or databases.
//Endless Scrolling: Implementing endless scrolling or pagination in your app.
//Optimized Memory Usage: Reducing memory usage by loading only the necessary data.
//like you have a lot of data and you did'nt want your thread get a lot of pressure by loading all data android make a solution ... so instead of fetching all the data in one time it will  fetch  data in Packets  like example 10 items in one time
///
//
//by implementing you need one class //that will inherit to PagingSource<Int ,DataItem>()
//in this class have two override function
// getRefreshKey

//private var totalNewsCount = 0
//override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
//    return state.anchorPosition?.let { anchorPosition->
//        val anchorPage = state.closestPageToPosition(anchorPosition)
//        anchorPage?.prevKey?.plus(1)?: anchorPage?.nextKey?.minus(1)
//    }
//}
// load
//override suspend fun load(params: PagingSource.LoadParams<Int>): PagingSource.LoadResult<Int, Article> {
//    val page = params.key ?:1
//    return try {
//        val newsResponse = newsApi.getNews(
//            source = sources,
//            page = page)
//
//        totalNewsCount+=newsResponse.articles.size
//        val articles = newsResponse.articles.distinctBy {it.title } // remove duplicate articles
//        PagingSource.LoadResult.Page(
//            data = articles,
//            nextKey = if(totalNewsCount ==newsResponse.totalResults) null else page +1,
//            prevKey = null
//        )
//
//    }catch (e:Exception){
//        e.printStackTrace()
//        PagingSource.LoadResult.Error(
//            throwable = e
//        )
//    }
//}
//now i want to access it from ui view models
//that's example
//override fun getNews(sources: List<String>): Flow<PagingData<Article>> {
//    return Pager(
//        config = PagingConfig(pageSize = 10),
//        pagingSourceFactory = {
//            NewsPagingSource(
//                newsApi = newsApi,
//                sources = sources.joinToString(separator = ",")
//            )
//        }
//    ).flow
//}
///thats for displaying in our composable like example
//articles : LazyPagingItems<Article>
///(LazPagingItems) just like a list but bcz we want to slow down the laoding work so thats why
