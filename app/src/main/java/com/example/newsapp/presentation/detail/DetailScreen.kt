package com.example.newsapp.presentation.detail

import android.content.Intent
import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardBackspace
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.newsapp.R
import com.example.newsapp.domain.model.Article
import com.example.newsapp.presentation.bookmark.BookmarkViewModel
import com.example.newsapp.presentation.common.Body
import com.example.newsapp.presentation.common.DetailScreenItem
import com.example.newsapp.presentation.common.ParallaxTopbar
import com.example.newsapp.ui.theme.CustomOrange
import com.example.newsapp.ui.theme.NewsAppTheme

@Composable
fun DetailScreen(
    bookmarkViewModel: BookmarkViewModel = hiltViewModel(),
    article: Article,
    event: (DetailEvent) -> Unit,
    navigateUp: () -> Unit
) {

    val context = LocalContext.current
    var animateClick by remember { mutableStateOf(false) }

    val color = if(bookmarkViewModel.isBookMarked(article)) CustomOrange else Color.White

            Box(
                modifier = Modifier
                    .fillMaxSize().clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ){
                        animateClick = !animateClick
                    }
            ) {
                ParallaxTopbar(article.urlToImage, context)
                Body(article) {
                    val sendIntent = Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra(Intent.EXTRA_TEXT, article.url)
                        type = "text/plain"
                    }
                    val shareIntent = Intent.createChooser(sendIntent, null)
                    context.startActivity(shareIntent)
                }

                AnimatedVisibility(
                    visible = animateClick,
                    enter = fadeIn() + slideInVertically(),
                    exit = fadeOut() + slideOutVertically(),
                ) {

                    Column(
                        modifier = Modifier.fillMaxSize()
                    ) {

                        Spacer(
                            modifier = Modifier
                                .height(70.dp)
                                .fillMaxWidth()
                                .background(
                                    brush =
                                    Brush.verticalGradient(
                                        listOf<Color>(Color.Black, Color.Black,Color.Transparent),
                                        ),
                                    alpha = 0.6f
                                )
                        )

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                        ) {

                            Icon(imageVector = Icons.AutoMirrored.Filled.KeyboardBackspace,
                                modifier = Modifier
                                    .clickable(
                                        indication = null,
                                        interactionSource = remember { MutableInteractionSource() }
                                    ) {
                                        navigateUp.invoke()
                                    }
                                    .size(30.dp),
                                contentDescription = null,
                                tint = Color.White)
                            Icon(painter = painterResource(R.drawable.bookmark), modifier = Modifier
                                .clickable {
                                    bookmarkViewModel.toggleBookmark(article = article)
                                    event(DetailEvent.InsertDeleteArticle(article))
                                }
                                .size(25.dp), contentDescription = null, tint = color)

                        }


                    }
                }



    }
}


@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DetailScreenPreview(modifier: Modifier = Modifier) {

    NewsAppTheme(dynamicColor = false) {

//        DetailScreen(article = Article(
//            source = Source(
//                id = "", name = "BBC"
//            ),
//            author = "Hassan",
//            title = "Why are you single?",
//            description = "Bcz we are born Single",
//            urlToImage = "",
//            content = "",
//            url = "https://consent.google.com/ml?continue=https://news.google.com",
//            publishedAt = "Monday 4:50 PM"
//        ), event = {},
//            state = ) {
//
//        }
    }
}