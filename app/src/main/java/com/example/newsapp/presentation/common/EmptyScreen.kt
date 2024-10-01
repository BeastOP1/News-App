package com.example.newsapp.presentation.common

import android.content.res.Configuration
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ImageSearch
import androidx.compose.material.icons.filled.NetworkCheck
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import com.example.newsapp.ui.theme.NewsAppTheme
import java.net.ConnectException
import java.net.SocketTimeoutException


@Composable
fun EmptyScreen(error: LoadState.Error? = null) {

    var message by remember {
        mutableStateOf(parseErrorMessage(error = error))
    }
    var icon by remember {
        mutableStateOf(Icons.Filled.NetworkCheck)
    }
    if (error == null) {
        message = "You have not saved news so far !"
        icon = Icons.Filled.ImageSearch
    }

    var startAnimation by remember {
        mutableStateOf(false)
    }

    val alphaAnimation by animateFloatAsState(
        targetValue = if (startAnimation) 0.3f else 0f,
        animationSpec = tween(durationMillis = 1000)
    )
    LaunchedEffect(key1 = true) {
        startAnimation = true
    }
    EmptyContent(alphaAnim = alphaAnimation, message =message , imageVector = icon )

}


@Composable
fun EmptyContent(alphaAnim: Float, message: String, imageVector: ImageVector) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = imageVector, contentDescription = null,
            tint = if (isSystemInDarkTheme()) Color.LightGray else Color.DarkGray,
            modifier = Modifier
                .size(120.dp)
                .alpha(alphaAnim)
        )
        Text(
            text = message,
            modifier = Modifier
                .padding(10.dp)
                .alpha(alphaAnim),
            style = MaterialTheme.typography.bodyMedium,
            color = if(isSystemInDarkTheme()) Color.LightGray else Color.DarkGray
        )

    }
}

fun parseErrorMessage(error: LoadState.Error?): String {
    return when (error?.error) {
        is SocketTimeoutException -> {
            "Server Unavailable"
        }

        is ConnectException -> {
            "Internet Unavailable"
        }

        else -> {
            "UnKnown Error"
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun EmptyPreview(modifier: Modifier = Modifier) {
 NewsAppTheme {
 EmptyContent(alphaAnim = 0.3f, message = "Check Internet Connecton", imageVector = Icons.Filled.ImageSearch)

 }
}