package com.example.newsapp.presentation.home

sealed class HomeUIEvents {

    data class OnCategoryChanged(var category: String): HomeUIEvents()


}