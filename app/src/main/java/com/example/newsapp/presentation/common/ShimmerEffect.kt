package com.example.newsapp.presentation.common

import android.annotation.SuppressLint
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.newsapp.presentation.onBoarding.components.Dimens
import com.example.newsapp.ui.theme.NewsAppTheme


@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.shimmerEffect() = composed {
    val transition = rememberInfiniteTransition()
    val alpha = transition.animateFloat(
        initialValue = 0.2f,
        targetValue = 0.9f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000),
            repeatMode = RepeatMode.Reverse
        )
    ).value
    background(
        color = if (isSystemInDarkTheme()) Color.DarkGray.copy(alpha = alpha) else Color.LightGray.copy(
            alpha = alpha
        )
    )
}


@Composable
fun ArticleCardShimmerEffect(modifier: Modifier) {

    Row(
        modifier = Modifier.padding(10.dp)
    ) {
        //coil library
        Box(
            modifier = Modifier
                .size(Dimens.ArticleCardSize)
                .clip(MaterialTheme.shapes.medium)
                .shimmerEffect(),
        )

        Column(
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .padding(horizontal = Dimens.ExtraSmallPadding)
                .height(Dimens.ArticleCardSize)
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp)
                    .padding(horizontal = Dimens.MediumPadding1)
                    .shimmerEffect(),
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .height(15.dp)
                        .padding(horizontal = Dimens.MediumPadding1)
                        .shimmerEffect(),
                )


            }


        }
    }
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Preview(showBackground = true)
@Composable
fun ShimmerPreview(modifier: Modifier = Modifier) {

    NewsAppTheme {
        ArticleCardShimmerEffect(modifier)
    }
}