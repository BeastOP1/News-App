package com.example.newsapp.ui.theme

import  com.example.newsapp.R
import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */


)

val NewsFontFamily = FontFamily(
    Font(R.font.inter_black,FontWeight.Normal),
    Font(R.font.inter_bold,FontWeight.Bold),
    Font(R.font.inter_semibold,FontWeight.SemiBold),
    Font(R.font.inter_extrabold,FontWeight.ExtraBold),
    Font(R.font.inter_light,FontWeight.Light),
    Font(R.font.inter_extralight,FontWeight.ExtraLight),
    Font(R.font.inter_thin,FontWeight.Thin),
    Font(R.font.inter_medium,FontWeight.Medium),
    Font(R.font.inter_regular,FontWeight.W100),
)

val NewsTypography =Typography(

    displayLarge = TextStyle(
        fontFamily = NewsFontFamily,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 30.sp
    ),
    displayMedium = TextStyle(
        fontFamily = NewsFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp
    ),
    displaySmall = TextStyle(
        fontFamily = NewsFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 26.sp
    ),

    // Headline Styles
    headlineLarge = TextStyle(
        fontFamily = NewsFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = NewsFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 22.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = NewsFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp
    ),

    // Title Styles
    titleLarge = TextStyle(
        fontFamily = NewsFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp
    ),
    titleMedium = TextStyle(
        fontFamily = NewsFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp
    ),
    titleSmall = TextStyle(
        fontFamily = NewsFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),

    // Body Styles
    bodyLarge = TextStyle(
        fontFamily = NewsFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = NewsFontFamily,
        fontWeight = FontWeight.Light,
        fontSize = 14.sp
    ),
    bodySmall = TextStyle(
        fontFamily = NewsFontFamily,
        fontWeight = FontWeight.Light,
        fontSize = 12.sp
    ),

    // Label Styles
    labelLarge = TextStyle(
        fontFamily = NewsFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp
    ),
    labelMedium = TextStyle(
        fontFamily = NewsFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),
    labelSmall = TextStyle(
        fontFamily = NewsFontFamily,
        fontWeight = FontWeight.Thin,
        fontSize = 10.sp
    )

)
