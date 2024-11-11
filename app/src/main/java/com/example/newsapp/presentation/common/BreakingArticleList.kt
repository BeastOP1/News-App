package com.example.newsapp.presentation.common

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.InfiniteTransition
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.example.newsapp.domain.model.Article

@Composable
fun BreakingArticleList(
    modifier: Modifier,
    articles: LazyPagingItems<Article>,
    onClick: (Article) -> Unit,
) {

    LazyRow(
        modifier = modifier.animateContentSize()
            .fillMaxWidth()
            .background(Color.Transparent),
        horizontalArrangement = Arrangement.spacedBy(24.dp),
        verticalAlignment = Alignment.CenterVertically,
        userScrollEnabled = true
    ) {

        items(articles.itemCount) {
            val filterArticle = articles.get(it)
            if (filterArticle?.source == null) {
                return@items
            }else {
                BreakingNewsItem(
                    article = filterArticle,
                    onCLick = {
                        onClick(filterArticle)
                    }
                )
            }


        }

    }
}

@PreviewLightDark
@Composable
fun BreakingArticleListPreview(modifier: Modifier = Modifier) {

}