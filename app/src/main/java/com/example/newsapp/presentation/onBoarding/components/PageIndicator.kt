package com.example.newsapp.presentation.onBoarding.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.newsapp.presentation.onBoarding.components.Dimens.IndicatorSize
import com.example.newsapp.ui.theme.CustomGray
import com.example.newsapp.ui.theme.CustomOrange

@Composable
fun PageIndicator(
    modifier: Modifier = Modifier,
    pageSize : Int,
    selectedPage: Int,
    selectedColor : Color = CustomOrange,
    unselectedColor : Color = CustomGray
                  ) {

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
    ) {
        repeat(pageSize){page ->
            Box(modifier = Modifier.size(IndicatorSize).clip(CircleShape).background(color =
            if(page == selectedPage){
                selectedColor
            }else unselectedColor) )
        }

    }

}