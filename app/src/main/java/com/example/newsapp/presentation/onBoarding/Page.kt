package com.example.newsapp.presentation.onBoarding

import androidx.annotation.DrawableRes
import com.example.newsapp.R

data class Page(
    val title : String,
    val description : String,
    @DrawableRes val image : Int


)

val pages = listOf(
    Page(
        title = "Welcome to News" ,
        description = "\" Discover the latest news from around the world.Stay informed with real -time updates and top stories tailored just for you.\"",
        image = R.drawable.first
    ),
    Page(
        title = "Customize Your Feed",
        description = "\" Choose topics that interest you, from sports to technology. Enjoy a personalized experience with news categories that matter most to you.  \"",
        image = R.drawable.second
    ),
    Page(
        title = "Stay Updated",
        description = "\"Get real-time access to the latest headlines and stories. Stay informed with up-to-the-minute news on topics that matter to you. \" ",
        image = R.drawable.third
    )
)