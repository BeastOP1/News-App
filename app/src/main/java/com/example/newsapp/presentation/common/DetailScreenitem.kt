package com.example.newsapp.presentation.common

import android.content.Context
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import com.example.newsapp.R
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardBackspace
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.newsapp.domain.model.Article
import com.example.newsapp.ui.theme.CustomOrange
import com.example.newsapp.ui.theme.NewsAppTheme

@Composable
fun Detail(modifier: Modifier = Modifier) {


}


private val headerHeight = 320.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ParallaxTopbar(urlToImage: String?, context: Context ){

//    var click = false
//    val headerHeightPx = with(LocalDensity.current) { headerHeight.toPx() }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(headerHeight)
    ) {

        AsyncImage(
            model = if (urlToImage == null) ImageRequest.Builder(context).data(R.drawable.image)
                .build() else ImageRequest.Builder(context).data(urlToImage).build(),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillBounds,
        )
//        Image(
//            painter = painterResource(id = R.drawable.img),
//            contentDescription = null,
//            contentScale = ContentScale.FillBounds,
//
//            )

    }

}

val text =
    "Tomorrow's Top 25 Today: Texas A&M vaults into top 10 as LSU plummets in college football rankings - CBS Sports" +
            " Projecting the new AP Top 25 college football rankings after Week 9 of the college football season"

@Composable
fun Body(article: Article?, onSharedClick: () -> Unit) {

    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxHeight()
    ) {

        Spacer(
            modifier = Modifier
                .height(headerHeight - 40.dp)
                .fillMaxWidth()
        )

        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .clip( RoundedCornerShape(25.dp))
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            Spacer(
                modifier = Modifier
                    .height(5.dp)
                    .fillMaxWidth()
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {

                Spacer(modifier = Modifier.width(15.dp))
                Spacer(
                    modifier = Modifier
                        .background(CustomOrange)
                        .width(3.dp)
                        .height(45.dp)
                        .padding(vertical = 4.dp)
                )
                Text(
                    color = MaterialTheme.colorScheme.onBackground,
                    text = if (article?.title == null) "Please wait a while" else article.title,
                    style = MaterialTheme.typography.headlineSmall,
                    textAlign = TextAlign.Justify,
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                )
            }

            Text(
                color = MaterialTheme.colorScheme.onBackground,
                text = if (article?.description == null) "Please wait a while" else article.description,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Justify,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )

            Text(
                color = MaterialTheme.colorScheme.onBackground,
                text = if (article?.content == null) "Please wait a while" else article.content,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Justify,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    painter = painterResource(R.drawable.share),
                    contentDescription = null,
                    tint = CustomOrange,
                    modifier = Modifier
                        .size(30.dp)
                        .clickable {
                            onSharedClick.invoke()
                        })

            }


        }

    }
}

@Composable
fun DetailScreenItem(
    article: Article,
    context: Context, // New parameter to determine bookmark state
    onSharedClick: () -> Unit,
    onBookmarkClick: () -> Unit,
    onBackClick: () -> Unit,
    animateClick : Boolean
) {

    var animate by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        ParallaxTopbar(article.urlToImage, context)
        Body(article, onSharedClick)

        AnimatedVisibility(
            visible = animateClick,
            enter = fadeIn() + slideInVertically(),
            exit = fadeOut() + slideOutVertically(),
        ) {

            Column(
                modifier = Modifier.fillMaxSize()
            ) {

                Spacer(modifier = Modifier.height(90.dp).fillMaxWidth().background(brush =
                Brush.verticalGradient(
                    listOf<Color>(Color.Black, Color.Transparent),

                    ),
                    alpha = 0.5f
                ))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .statusBarsPadding()
                        .height(100.dp)
                        .padding(horizontal = 16.dp),
                ) {

                    Icon(imageVector = Icons.AutoMirrored.Filled.KeyboardBackspace,
                        modifier = Modifier
                            .clickable(
                                indication = null,
                                interactionSource = remember { MutableInteractionSource() }
                            ) {
                                onBackClick.invoke()
                            }
                            .size(30.dp),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.background)
                    Icon(painter = painterResource(R.drawable.bookmark), modifier = Modifier
                        .clickable {
                            onBookmarkClick.invoke()
                        }
                        .size(25.dp), contentDescription = null, tint = CustomOrange)


                }




            }
        }
    }
}

//@PreviewLightDark
@Composable
fun PreviewDetail(article: Article) {
    val scroll = rememberScrollState(0)
    NewsAppTheme {


    }
}