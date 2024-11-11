package com.example.newsapp.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.newsapp.R
import com.example.newsapp.presentation.onBoarding.components.Dimens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopBar(modifier: Modifier = Modifier, navigateToSearch :() -> Unit) {


    TopAppBar(

        modifier = Modifier.fillMaxWidth().background(brush = Brush.linearGradient(
            listOf(Color.Cyan , Color.Yellow)
        )),
        title = {

        SearchBar(
            modifier = Modifier.padding(horizontal = Dimens.MediumPadding1),
            text = "",
            readOnly = true,
            onValueChange = {},
            onClick = {
                navigateToSearch()
            },
            onSearch = {},
            onBack = {},
            onClearClick = {}
        )

    },




    )
}