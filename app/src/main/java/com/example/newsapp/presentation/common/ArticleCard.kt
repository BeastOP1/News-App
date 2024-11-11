package com.example.newsapp.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DatePickerFormatter
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.newsapp.domain.model.Article
import com.example.newsapp.domain.model.Source
import com.example.newsapp.presentation.onBoarding.components.Dimens.ArticleCardSize
import com.example.newsapp.presentation.onBoarding.components.Dimens.ExtraSmallPadding
import com.example.newsapp.ui.theme.CustomBlack
import com.example.newsapp.ui.theme.CustomGray
import com.example.newsapp.ui.theme.CustomOrange
import com.example.newsapp.ui.theme.CustomWhite
import com.example.newsapp.ui.theme.NewsAppTheme
import java.text.DateFormat
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun ArticleCard(
    modifier: Modifier = Modifier,
    article: Article,
    onClick: () -> Unit,
) {

    val context = LocalContext.current
    Row(
        modifier = Modifier.clickable { onClick() }.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        //coil library

        AsyncImage(
            modifier = Modifier
                .size(ArticleCardSize)
                .clip(MaterialTheme.shapes.medium),
            contentScale = ContentScale.Crop,
            model = ImageRequest.Builder(context = context).data(article.urlToImage).build(),
            contentDescription = null
        )

        Spacer(modifier = Modifier
            .height(55.dp)
            .width(3.dp)
            .background(CustomOrange))

        Column(
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .padding(horizontal = ExtraSmallPadding)
                .height(ArticleCardSize)
        ) {

            Text(
                text = article.title, style = MaterialTheme.typography.bodyMedium,
                color = if (isSystemInDarkTheme()) CustomWhite else CustomBlack,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )


                Spacer(modifier = Modifier.width(ExtraSmallPadding))

                Text(
                    text = changeDateFormat(article.publishedAt),
                    style = MaterialTheme.typography.labelLarge,
                    color = if (isSystemInDarkTheme()) CustomGray else CustomGray
                )





        }
    }


}

fun changeDateFormat(publishedAt: String): String{
    val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
    val outputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy - hh:mm a")
    val dateTime = LocalDateTime.parse(publishedAt, inputFormatter)

    return dateTime.atOffset(ZoneOffset.UTC).format(outputFormatter)
}
@PreviewLightDark
@Composable
fun ArticlePreview() {

    NewsAppTheme {
        Column(
            modifier = Modifier.fillMaxSize().background(CustomWhite)
            ,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {

            Spacer(modifier = Modifier.fillMaxWidth().background(CustomOrange))
            ArticleCard(
                article = Article(
                    author = "",
                    source = Source(id = "", name = "BBc"),
                    title = "mY Name is , why  name is so cool because you are beautiful",
                    description = "",
                    content = "",
                    url = "",
                    urlToImage = "",
                    publishedAt = "2 hours"
                ),
                onClick = {}
            )

            ArticleCard(
                article = Article(
                    author = "",
                    source = Source(id = "", name = "BBc"),
                    title = "mY Name is , why  name is so cool because you are beautiful",
                    description = "",
                    content = "",
                    url = "",
                    urlToImage = "",
                    publishedAt = "2 hours"
                ),
                onClick = {}
            )
        }
            }
}