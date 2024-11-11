package com.example.newsapp.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.newsapp.domain.model.Article
import com.example.newsapp.presentation.home.HomeUIState
import com.example.newsapp.presentation.onBoarding.components.Dimens
import com.example.newsapp.presentation.onBoarding.components.Dimens.ExtraSmallPadding2
import com.example.newsapp.presentation.onBoarding.components.Dimens.MediumPadding1
import com.example.newsapp.presentation.onBoarding.components.Dimens.MediumPadding2
import kotlinx.coroutines.flow.Flow


@Composable
fun ArticleList(
    modifier: Modifier,
    articles: List<Article>,
    onClick: (Article) -> Unit
) {


    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(MediumPadding1),
        contentPadding = PaddingValues(all = ExtraSmallPadding2)
    ) {

        items(count = articles.size) {
            val article = articles[it]
            ArticleCard(article = article, onClick = { onClick(article) })

        }
    }

}

@Composable
fun ArticleList(
    modifier: Modifier ,
    articles: LazyPagingItems<Article>,
    onClick: (Article) -> Unit
) {

    val handlePagingResult = handlePagingResult(articles = articles)
    if (handlePagingResult) {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(MediumPadding1),
            contentPadding = PaddingValues(all = ExtraSmallPadding2)
        ) {

            items(count = articles.itemCount) {
                articles[it]?.let {
                    ArticleCard(article = it, onClick = { onClick(it) })
                }
            }
        }
    }
}


@Composable
fun ArticleList(
    modifier: Modifier ,
    state: HomeUIState,
    onClick: (Article) -> Unit
) {

    val articlesFlow: Flow<PagingData<Article>> = state.articles
    val articles = articlesFlow.collectAsLazyPagingItems()

    val handlePagingResult = handlePagingResult(articles = articles)
    if (handlePagingResult) {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(MediumPadding1),
            contentPadding = PaddingValues(all = ExtraSmallPadding2)
        ) {



                items(count = articles.itemCount) {

                    val filterArticle = articles.get(it)
                    if(filterArticle?.source == null){
                        return@items
                    }else {
                        ArticleCard(article = filterArticle, onClick = { onClick(filterArticle) })

                    }

                }

        }
    }
}

@Composable
fun handlePagingResult(

    articles: LazyPagingItems<Article>
): Boolean {

    val loadState = articles.loadState
    val error = when {
        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
        else -> null
    }
    return when {
        loadState.refresh is LoadState.Loading -> {

            ShimmerEffect()
            false
        }

        error != null -> {
            EmptyScreen()
            false
        }

        else -> {
            true
        }
    }


}


@Composable
private fun ShimmerEffect(modifier: Modifier = Modifier) {

    Column(
        modifier = Modifier.fillMaxSize().padding(start = MediumPadding2, top = MediumPadding2),
        verticalArrangement = Arrangement.spacedBy(MediumPadding1),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        repeat(10) {
            ArticleCardShimmerEffect(modifier = Modifier)
        }

    }

}