package com.example.newsapp.presentation.home

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.*
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemContentType
import com.example.newsapp.R
import com.example.newsapp.domain.model.Article
import com.example.newsapp.presentation.common.ArticleList
import com.example.newsapp.presentation.common.BreakingArticleList
import com.example.newsapp.presentation.common.CustomTabRow
import com.example.newsapp.presentation.common.HomeScreenShimmerEffect
import com.example.newsapp.presentation.onBoarding.components.Dimens.MediumPadding1
import com.example.newsapp.ui.theme.CustomOrange
import kotlinx.coroutines.launch


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    headArticles: LazyPagingItems<Article>,
    state: HomeUIState,
    onEvent: (HomeUIEvents) -> Unit,
    navigateToDetail: (Article) -> Unit,
    navigateToSearch : () -> Unit
) {

    val categories = listOf(
        "general", "business", "entertainment", "health", "science", "sports", "technology"
    )
    val pagerState = rememberPagerState(initialPage = 0){
        categories.size
    }

    val coroutineScope = rememberCoroutineScope()

    var animateSearch by remember { mutableStateOf(false) }
    val searchSize by animateDpAsState(
        targetValue =  if(animateSearch) 45.dp else 30.dp,
        animationSpec = spring(dampingRatio = Spring.DampingRatioLowBouncy, stiffness = Spring.StiffnessHigh),
        label = "Size"
    )

    val searchAlpha by animateFloatAsState(
        targetValue =  if(animateSearch) 0.1f else 1f,
        animationSpec = spring(dampingRatio = Spring.DampingRatioLowBouncy, stiffness = Spring.StiffnessHigh),
        label = "x Alpha"

    )

    val searchXOffset by animateDpAsState(
        targetValue =  if(animateSearch) -120.dp else 0.dp,
        animationSpec = spring(dampingRatio = Spring.DampingRatioHighBouncy, stiffness = Spring.StiffnessHigh),
        label = "x Offset"

    )
    LaunchedEffect(key1 = pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { page ->
            onEvent(HomeUIEvents.OnCategoryChanged(category = categories[page]))

        }
    }

if(headArticles.itemCount >0){

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = MediumPadding1)
            .statusBarsPadding()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth().padding(horizontal = MediumPadding1),
            verticalAlignment = Alignment.CenterVertically,

        ) {
            Image(
                painter = painterResource(R.drawable.news),
                contentDescription = "Logo",
                modifier = Modifier.size(110.dp)
            )

            Spacer(modifier = Modifier.weight(1f))

            Icon(
                painter = painterResource(R.drawable.search),
                tint = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.size(searchSize).offset(x = searchXOffset).alpha(alpha = searchAlpha).clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ){
                    animateSearch = !animateSearch
                    navigateToSearch.invoke()
                },
                contentDescription = null
            )
        }

        Text(
            text = "Breaking News", style = MaterialTheme.typography.headlineLarge, color = MaterialTheme.colorScheme.onBackground, modifier = Modifier.padding(horizontal = MediumPadding1)
        )
        Spacer(modifier = Modifier.height(MediumPadding1))


        BreakingArticleList(
            modifier = Modifier.padding(start = 24.dp),
            articles = headArticles,
            onClick = {
                navigateToDetail(it)
            }
        )
        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            CustomTabRow(
                pagerState = pagerState,
                category = categories,
                onTabSelected = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(it)
                    }
                }
            )
            HorizontalPager(
                flingBehavior = PagerDefaults.flingBehavior(state = pagerState),
                state = pagerState,
                modifier = Modifier.weight(1f),

                ){ index ->

                ArticleList(
                    modifier = Modifier.padding(top=MediumPadding1, start = MediumPadding1, end = MediumPadding1),
                    state = state,
                ) {
                    navigateToDetail(it)
                }

            }

        }


    }
}
    else {
    HomeScreenShimmerEffect(modifier = Modifier)
    }


}