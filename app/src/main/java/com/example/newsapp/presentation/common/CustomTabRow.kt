package com.example.newsapp.presentation.common
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.waterfall
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Biotech
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.SportsFootball
import androidx.compose.material.icons.outlined.Biotech
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.SportsFootball
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabPosition
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.newsapp.presentation.onBoarding.components.Dimens
import com.example.newsapp.presentation.onBoarding.components.Dimens.MediumPadding1
import com.example.newsapp.ui.theme.CustomBlack
import com.example.newsapp.ui.theme.CustomDark
import com.example.newsapp.ui.theme.CustomOrange
import com.example.newsapp.ui.theme.CustomWhite


val tabItems = listOf(
    TabItem(
        name = "My Feed",
        selectedIcon = Icons.Default.Home,
        unselectedIcon = Icons.Outlined.Home
    ),
    TabItem(
        name = "Science",
        selectedIcon = Icons.Default.Biotech,
        unselectedIcon = Icons.Outlined.Biotech
    ),
    TabItem(
        name = "Sport",
        selectedIcon = Icons.Default.SportsFootball,
        unselectedIcon = Icons.Outlined.SportsFootball
    )

)

@Composable
fun FancyIndicator(color: Color, modifier: Modifier = Modifier) {
    // Draws a rounded rectangular with border around the Tab, with a 5.dp padding from the edges
    // Color is passed in as a parameter [color]
    Box(
        modifier
            .padding(5.dp)
            .fillMaxSize()
            .border(BorderStroke(2.dp, color), RoundedCornerShape(5.dp))
    )
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CustomTabRow(
    pagerState: PagerState,
    category: List<String>,
    onTabSelected:(Int)-> Unit
) {

    val indicator = @Composable{ tabPositions: List<TabPosition> ->
        FancyIndicator(color = CustomOrange, Modifier.tabIndicatorOffset(tabPositions[pagerState.currentPage]))
    }
    Column(
        modifier = Modifier.wrapContentHeight()
    ) {

        ScrollableTabRow(
            selectedTabIndex = pagerState.currentPage,
            modifier = Modifier.padding(start = Dimens.MediumPadding2),
            containerColor = if(isSystemInDarkTheme()) CustomDark else CustomWhite,
            contentColor = MaterialTheme.colorScheme.onBackground,
            edgePadding = 0.dp,

            indicator = {
               TabRowDefaults.Indicator(
                   color = CustomOrange,
                   modifier = Modifier.tabIndicatorOffset(currentTabPosition = it[pagerState.currentPage])
               )
            }
        ) {
            category.forEachIndexed { index ,category ->

                Tab(
                    selected = pagerState.currentPage ==index ,
                    onClick = {
                        onTabSelected(index)
                    },
                    text = {
                        Text(text = if(category == "general") "My Feed" else category.capitalize(), color = if(index == pagerState.currentPage) CustomOrange else MaterialTheme.colorScheme.onBackground , style = MaterialTheme.typography.bodyMedium )
                    },
                )
            }
        }


    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CustomAuthTab(pagerState: PagerState, onTabSelected: (Int) -> Unit, screen: List<String>) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        TabRow(
            selectedTabIndex = pagerState.currentPage,
            modifier = Modifier,
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.onBackground,
            indicator = {
                TabRowDefaults.Indicator(
                    color = CustomOrange,
                    modifier = Modifier.tabIndicatorOffset(currentTabPosition = it[pagerState.currentPage])
                )
            }
        ) {

            screen.forEachIndexed { index, screen ->
                Tab(
                    selected = pagerState.currentPage == index,
                    text = {
                        Text(text = screen, )
                    },
                    onClick = {
                        onTabSelected(index)
                    })

            }
        }

    }
}
data class TabItem(
    val name: String,
    val unselectedIcon: ImageVector,
    val selectedIcon: ImageVector
)