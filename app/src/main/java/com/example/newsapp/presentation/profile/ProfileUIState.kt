package com.example.newsapp.presentation.profile


data class ProfileUIState(
    val notificationEnabled: Boolean = true,
    val hdEnabled : Boolean = false,
    val mode: Boolean = false,
    val autoPlay : Boolean = false,

    val languageClicked : Boolean = false,
    val feedClicked : Boolean = false,
    val textSizeClicked : Boolean = false,
    val shareClicked : Boolean = false,
    val alertDialog : Boolean = false
)
