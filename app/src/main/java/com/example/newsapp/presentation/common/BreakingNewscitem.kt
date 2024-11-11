package com.example.newsapp.presentation.common

import android.content.Context
import android.graphics.fonts.FontStyle
import android.opengl.Visibility
import androidx.compose.animation.core.VisibilityThreshold
import com.example.newsapp.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.newsapp.domain.model.Article
import com.example.newsapp.ui.theme.CustomBlack
import com.example.newsapp.ui.theme.CustomOrange
import com.example.newsapp.ui.theme.CustomWhite
import com.example.newsapp.ui.theme.NewsAppTheme
import com.example.newsapp.ui.theme.NewsFontFamily

@Composable
fun BreakingNewsItem(
    article: Article,
    onCLick:()-> Unit
) {
    val context = LocalContext.current
    Box(
        modifier = Modifier.clickable{
            onCLick()
        }
            .width(240.dp)
            .height(160.dp)
            .clip(RoundedCornerShape(20.dp)),

        ) {

        AsyncImage(
            contentDescription = article.description,
            modifier = Modifier.fillMaxWidth(),
            alpha = DefaultAlpha,
            colorFilter = ColorFilter.colorMatrix(colorMatrix = ColorMatrix()),
            contentScale = ContentScale.Crop,
            model = ImageRequest.Builder(context = context).data(article.urlToImage).build()
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        brush = Brush.verticalGradient(
                            listOf(  Color.Black, Color.Transparent )
                        )
                        ,
                        alpha = 0.6f
                    ),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .height(15.dp))
            }
            Spacer(modifier = Modifier.weight(1f))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        brush = Brush.verticalGradient(
                            listOf(Color.Transparent,Color.Black, Color.Black)
                        ),
                        alpha = 0.6f
                    )
                    .padding(horizontal = 13.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = article.title,
                    color = CustomWhite,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 2
                )

            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        brush = Brush.verticalGradient(
                            listOf(  Color(0x940C0B0B),CustomBlack, )
                        )
                    ),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .height(22.dp))
            }


        }

    }


}

@PreviewLightDark
@Composable
fun PreviewBreakingNewsItem(modifier: Modifier = Modifier) {

    NewsAppTheme {
//        BreakingNewsItem()

    }
}