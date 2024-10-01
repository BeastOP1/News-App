package com.example.newsapp.presentation.detail.component

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Bookmarks
import androidx.compose.material.icons.filled.GroupWork
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.WifiTetheringOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.newsapp.ui.theme.NewsAppTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailTopBar(
    isBookmarked: Boolean, // New parameter to determine bookmark state
    onBrowsingClick:() -> Unit,
    onSharedClick:() -> Unit,
    onBookmarkClick: () -> Unit,
    onBackClick : () -> Unit
) {


    TopAppBar(title = { /*TODO*/ } ,

        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = if(isSystemInDarkTheme())Color.Black else Color.White,
            actionIconContentColor = if(isSystemInDarkTheme())Color.LightGray else Color.DarkGray,
            navigationIconContentColor =  if(isSystemInDarkTheme())Color.White else Color.Black,

        ),
        modifier = Modifier.fillMaxWidth()
        ,
        navigationIcon = {
            IconButton(onClick = { onBackClick() }) {
                Icon(imageVector = Icons.Filled.ArrowBackIosNew, contentDescription = null)

            }
        },
        actions = {
            IconButton(onClick = { onBookmarkClick() }) {
                Icon(imageVector = Icons.Filled.Bookmarks, contentDescription = null
                    ,
                    tint = if(isBookmarked) Color.Yellow else Color.Gray
                )

            }
            IconButton(onClick = { onSharedClick() }) {
                Icon(imageVector = Icons.Filled.Share, contentDescription = null)

            }
            IconButton(onClick = { onBrowsingClick() }) {
                Icon(imageVector = Icons.Filled.WifiTetheringOff, contentDescription = null)

            }
        }
    )

}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DetailTopBarPreview(modifier: Modifier = Modifier) {


    NewsAppTheme {

        DetailTopBar(
            onBrowsingClick = { /*TODO*/ },
            onSharedClick = { /*TODO*/ },
            onBookmarkClick = { /*TODO*/ },
            isBookmarked = false) {
            
        }
    }
}