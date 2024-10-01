package com.example.newsapp.presentation.common

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.WatchLater
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.newsapp.domain.model.Article
import com.example.newsapp.domain.model.Source
import com.example.newsapp.presentation.onBoarding.components.Dimens.ArticleCardSize
import com.example.newsapp.presentation.onBoarding.components.Dimens.ExtraSmallPadding
import com.example.newsapp.presentation.onBoarding.components.Dimens.ExtraSmallPadding2
import com.example.newsapp.presentation.onBoarding.components.Dimens.SmallIconSize
import com.example.newsapp.ui.theme.NewsAppTheme

@Composable
fun ArticleCard(
    modifier: Modifier = Modifier,
    article: Article,
    onClick: () -> Unit,
) {

    val context = LocalContext.current
    Row(
        modifier = modifier.clickable { onClick() }
    ) {
        //coil library

        AsyncImage(
            modifier = Modifier
                .size(ArticleCardSize)
                .clip(MaterialTheme.shapes.medium),
            contentScale = ContentScale.Crop,
            model = ImageRequest.Builder(context =context ).data(article.urlToImage).build(),
            contentDescription = null)

        Column(
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .background(if (isSystemInDarkTheme()) Color.Black else Color.Transparent)
                .padding(horizontal = ExtraSmallPadding)
                .height(ArticleCardSize)        ) {
            
            Text(text = article.title, style = MaterialTheme.typography.bodyMedium ,
                color = if (isSystemInDarkTheme()) Color.White else Color.Black,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
                )

            Row(
                verticalAlignment =  Alignment.CenterVertically
            ) {

                Text(
                    text = article.source.name,
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                    color = if (isSystemInDarkTheme()) Color.White else Color.Black,

                    )

                Spacer(modifier = Modifier.width(ExtraSmallPadding2))

                Icon(
                    imageVector = Icons.Filled.WatchLater,
                    contentDescription = null,
                    modifier = Modifier.size(SmallIconSize),
                    tint = if (isSystemInDarkTheme()) Color.White else Color.Black
                    )

                Spacer(modifier = Modifier.width(ExtraSmallPadding2))

                Text(text = article.publishedAt,
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold) ,
                    color = if (isSystemInDarkTheme()) Color.White else Color.Black
                )


            }


        }
    }



}
@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun ArticlePreview() {

    NewsAppTheme {
        ArticleCard(article =  Article(

            author = "",
            source = Source(id = "", name = "BBc"),
            title = "mY Name is , why  name is so cool",
            description = "",
            content = "",
            url = "",
            urlToImage = "",
            publishedAt = "2 hours"
        )
        ) {
            
        }
    }
}