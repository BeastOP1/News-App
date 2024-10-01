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
        title = "loream>3" ,
        description = "Ahmed",
        image = R.drawable.ahmed
    ),
    Page(
        title = "loreeam ",
        description = "Umair",
        image = R.drawable.umair
    ),
    Page(
        title = "loreeam",
        description = "Imran",
        image = R.drawable.imran
    )
)