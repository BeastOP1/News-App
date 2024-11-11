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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.example.newsapp.presentation.onBoarding.components.Dimens
import com.example.newsapp.presentation.onBoarding.components.Dimens.ArticleCardSize
import com.example.newsapp.presentation.onBoarding.components.Dimens.ExtraSmallPadding
import com.example.newsapp.presentation.onBoarding.components.Dimens.MediumPadding1
import com.example.newsapp.presentation.onBoarding.components.Dimens.MediumPadding2
import com.example.newsapp.ui.theme.NewsAppTheme


@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.shimmerEffect() = composed {
    val transition = rememberInfiniteTransition()
    val alpha = transition.animateFloat(
        initialValue = 0.1f,
        targetValue = 1f,
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
        modifier = Modifier
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
                .padding(horizontal = ExtraSmallPadding)
                .height(ArticleCardSize)
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .padding(horizontal = MediumPadding1)
                    .shimmerEffect(),
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .height(20.dp)
                        .padding(horizontal = MediumPadding1)
                        .shimmerEffect(),
                )


            }


        }
    }
}


@Composable
fun HomeScreenShimmerEffect(modifier: Modifier = Modifier) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(MediumPadding1)
    ) {

        Spacer(modifier= Modifier.height(50.dp))
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = MediumPadding2),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier.width(80.dp).height(25.dp).shimmerEffect()
            ) {  }

            Row(
                modifier = Modifier.width(30.dp).height(25.dp).shimmerEffect()
            ) {  }
        }

        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = MediumPadding2),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier.width(140.dp).height(25.dp).shimmerEffect()
            ) {  }

        }

        LazyRow(
            modifier = Modifier.fillMaxWidth().padding(start = MediumPadding2),
            horizontalArrangement = Arrangement.spacedBy(MediumPadding1)
        ) {

            items(2){
                Box(modifier = Modifier.height(160.dp).width(240.dp).clip(RoundedCornerShape(16.dp)).shimmerEffect())
            }
        }


        LazyRow(
            modifier = Modifier.fillMaxWidth().padding(start = MediumPadding2),
            horizontalArrangement = Arrangement.spacedBy(MediumPadding1),
            verticalAlignment = Alignment.CenterVertically
        ) {
            items(5){
                Row(

                    modifier = Modifier.width(60.dp).height(20.dp).shimmerEffect()
                ) {  }
            }
        }

        LazyColumn(
            modifier = Modifier.weight(1f).padding(start = MediumPadding2),
            verticalArrangement = Arrangement.spacedBy(MediumPadding1),
        ) {

            items(5){
                ArticleCardShimmerEffect(modifier)
            }
        }
    }
}

@PreviewLightDark()
@Composable
fun ShimmerPreview(modifier: Modifier = Modifier) {

    NewsAppTheme {
        HomeScreenShimmerEffect()
    }
}