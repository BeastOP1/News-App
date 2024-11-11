package com.example.newsapp.presentation.search
import com.example.newsapp.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.newsapp.domain.model.Article
import com.example.newsapp.presentation.common.ArticleList
import com.example.newsapp.presentation.common.SearchBar
import com.example.newsapp.presentation.onBoarding.components.Dimens.MediumPadding1
import com.example.newsapp.presentation.onBoarding.components.Dimens.MediumPadding2
import com.example.newsapp.ui.theme.CustomGray
import com.example.newsapp.ui.theme.CustomOrange

@Composable
fun SearchScreen(
    state:SearchState,
    event : (SearchEvent) -> Unit,
    navigateToDetails : (Article) -> Unit,
    navController: NavController,

) {

    val focusManger = LocalFocusManager.current
    Column(
        modifier = Modifier
            .fillMaxSize().statusBarsPadding()
            .padding(horizontal = MediumPadding1, vertical = MediumPadding1),
        verticalArrangement = Arrangement.spacedBy(MediumPadding1),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SearchBar(text = state.searchQuery , onSearch = {
            event(SearchEvent.SearchNews)
            focusManger.clearFocus()
        }, onValueChange = {
            event(SearchEvent.UpdateSearchQuery(it))
        }, onClick = {}, readOnly = false, modifier = Modifier, onBack = {
            focusManger.clearFocus()
            navController.navigateUp()
        },
            onClearClick = {
                event(SearchEvent.UpdateSearchQuery(""))
                focusManger.clearFocus()
            })
        Column(
            modifier = Modifier
                .weight(1f).padding(start = MediumPadding2 +10.dp, end = 0.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.weight(1f))
                Text(text = "Clear all",style = MaterialTheme.typography.bodyMedium, color = CustomOrange, modifier = Modifier.clickable{
                    event(SearchEvent.UpdateSearchQuery("")
                    )
                    focusManger.clearFocus()

                })

            }




            state.articles?.let { articles ->
                val article = articles.collectAsLazyPagingItems()
                ArticleList(
                    modifier = Modifier,
                    articles = article
                ) {
                    navigateToDetails(it)
                }
            }
            if(state.searchQuery != ""){

                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    Image(
                        painter = painterResource(R.drawable.no_result) ,
                        contentDescription = null
                    )
                }
            }


        }
    }

//    Column(
//        modifier = Modifier
//            .fillMaxSize().background(if(isSystemInDarkTheme()) Color.Black else Color.White)
//            .padding(
//                top = MediumPadding1,
//                end = MediumPadding1,
//                start = MediumPadding1
//            )
//            .statusBarsPadding()
//    ) {
//
//        SearchBar(
//            text = state.searchQuery,
//            readOnly = false,
//            onValueChange ={
//                event(SearchEvent.UpdateSearchQuery(it))
//            },
//             onSearch = {
//                 event(SearchEvent.SearchNews)
//             }
//
//
//        )
//
//        Spacer(modifier = Modifier.height(MediumPadding1))
//
//
//        state.articles?.let {
//            val articles = it.collectAsLazyPagingItems()
//            ArticleList(
//                modifier = Modifier,
//                articles = articles
//            ) {
//                navigateToDetails(it)
//            }
//
//        }
//    }
}