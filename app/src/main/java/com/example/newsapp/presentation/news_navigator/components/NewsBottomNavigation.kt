package com.example.newsapp.presentation.news_navigator.components

import com.example.newsapp.R
import androidx.compose.foundation.background
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.example.newsapp.presentation.onBoarding.components.Dimens
import com.example.newsapp.presentation.onBoarding.components.Dimens.IconSize
import com.example.newsapp.ui.theme.CustomBlack
import com.example.newsapp.ui.theme.CustomBottomColor
import com.example.newsapp.ui.theme.CustomDark
import com.example.newsapp.ui.theme.CustomGray
import com.example.newsapp.ui.theme.CustomLight
import com.example.newsapp.ui.theme.CustomOrange
import com.example.newsapp.ui.theme.CustomWhite
import com.example.newsapp.ui.theme.NewsAppTheme


@Composable
fun NewsBottomNavigation(
    items: List<BottomNavigationItem>,
    selected: Int,
    onItemClick: (Int) -> Unit

) {

//    val brush = if(isSystemInDarkTheme())Brush.verticalGradient(listOf( Color.Transparent, CustomDark)) else Brush.verticalGradient(listOf( Color.LightGray, CustomLight))
    NavigationBar(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .clip(RoundedCornerShape(28.dp))
            .background(
                color = if(isSystemInDarkTheme()) CustomBottomColor else CustomLight
            )
            
            .padding(horizontal = Dimens.MediumPadding2),
        containerColor = Color.Transparent,
        tonalElevation =50.dp,

        windowInsets = WindowInsets(bottom = 0.dp, top = 0.dp , left = 0.dp , right = 0.dp)
    ) {

        items.forEachIndexed { index, item ->

            NavigationBarItem(
                modifier = Modifier.height(120.dp),
                selected = index == selected,
                onClick = { onItemClick(index) },
                icon = {
                    if (selected == index)
                        Icon(
                            painter = painterResource(item.icon),
                            contentDescription = null,
                            Modifier.size(IconSize),
                            tint =
                            CustomOrange
                        )
                    else
                        Icon(
                            painter = painterResource(item.icon),
                            contentDescription = null,
                            Modifier.size(IconSize),
                            tint =
                            CustomGray
                        )

                },

                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = CustomOrange,
                    unselectedIconColor = CustomGray,
                    selectedTextColor = CustomOrange,
                    unselectedTextColor = CustomGray,
                    indicatorColor = if (isSystemInDarkTheme()) CustomBottomColor else CustomWhite ,

                )
            )
        }

    }

}


data class BottomNavigationItem(
    val icon: Int,
    val text: String
)


@PreviewLightDark
@Composable
fun NewsBottomNavigationPreView(modifier: Modifier = Modifier) {

    NewsAppTheme {
        NewsBottomNavigation(items = listOf(
            BottomNavigationItem(icon = R.drawable.home, text = "Home"),
            BottomNavigationItem(icon = R.drawable.search, text = "Search"),
            BottomNavigationItem(icon = R.drawable.person, text = "Bookmark")
        ),
            selected = 1,
            onItemClick = {

            })
    }
}