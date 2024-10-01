package com.example.newsapp.presentation.detail

import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat.startActivity
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.newsapp.domain.model.Article
import com.example.newsapp.domain.model.Source
import com.example.newsapp.presentation.bookmark.BookmarkState
import com.example.newsapp.presentation.bookmark.BookmarkViewModel
import com.example.newsapp.presentation.detail.component.DetailTopBar
import com.example.newsapp.presentation.onBoarding.components.Dimens.ArticleImageHeight
import com.example.newsapp.presentation.onBoarding.components.Dimens.MediumPadding1
import com.example.newsapp.ui.theme.NewsAppTheme

@Composable
fun DetailScreen(
    bookmarkViewModel: BookmarkViewModel = hiltViewModel(),
    detailViewModel: DetailViewModel = hiltViewModel(),
    article: Article,
    event:(DetailEvent) -> Unit,
    navigateUp : () -> Unit
) {

    val context = LocalContext.current

    val sideEffect by detailViewModel.sideEffect.collectAsState()

    val isBookedMarked = bookmarkViewModel.isBookmarked(article= article)

    // Handle side effects, e.g., show a Toast
    LaunchedEffect(sideEffect) {
        sideEffect?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            detailViewModel.omEvent(DetailEvent.RemoveSideEffect) // Clear side effect after showing
        }
    }
    Column(
        modifier = Modifier
            .background(if (isSystemInDarkTheme()) Color.DarkGray else Color.Transparent)
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        
        DetailTopBar(
            isBookmarked = isBookedMarked,
            onBrowsingClick = {
                              Intent(Intent.ACTION_VIEW).also {
                                  it.data = Uri.parse(article.url)
                                  if(it.resolveActivity(context.packageManager) != null){
                                      context.startActivity(it)
                                  }
                              }
            },
            onSharedClick = {

                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, article.url)
                    type = "text/plain"
                }

                val shareIntent = Intent.createChooser(sendIntent, null)

//                if(sendIntent.resolveActivity(context.packageManager) != null){
                    context.startActivity(shareIntent)
//                }
//                            Intent(Intent.ACTION_SEND).also {
//                                it.putExtra(Intent.EXTRA_TEXT,article.url)
//                                it.type = "text/plain"
//                                if (it.resolveActivity(context.packageManager) != null){
//                                    context.startActivity(it)
//                                }
//                            }
            },
            onBookmarkClick = {
                bookmarkViewModel.toggleBookmark(article = article)
                event(DetailEvent.InsertDeleteArticle(article))

            },
            onBackClick = navigateUp)


        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(
                start = MediumPadding1,
                end = MediumPadding1,
                top = MediumPadding1
            )
        ) {

            item { 
                AsyncImage(model = ImageRequest.Builder(context = context).data(article.urlToImage).build(),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(ArticleImageHeight)
                        .clip(MaterialTheme.shapes.medium),
                    contentScale = ContentScale.Crop
                )
                
                Spacer(modifier = Modifier.height(MediumPadding1))

                Text(
                    text = article.title ,
                    style = MaterialTheme.typography.displaySmall,
                    color = if(isSystemInDarkTheme()) Color.White else Color.Black
                    )
                Text(
                    text = article.description ,
                    style = MaterialTheme.typography.bodyMedium,
                    color = if(isSystemInDarkTheme()) Color.White else Color.Black
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DetailScreenPreview(modifier: Modifier = Modifier) {

    NewsAppTheme (dynamicColor = false){

        DetailScreen(article = Article(
            source = Source(
                id = "", name = "BBC"
            ),
            author = "Hassan",
            title = "Why are you single?",
            description = "Bcz we are born Single",
            urlToImage = "",
            content = "",
            url = "https://consent.google.com/ml?continue=https://news.google.com",
            publishedAt = "Monday 4:50 PM"
        ), event = {}) {
            
        }
    }
}