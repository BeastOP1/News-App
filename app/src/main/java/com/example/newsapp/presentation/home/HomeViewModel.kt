package com.example.newsapp.presentation.home

import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.newsapp.domain.model.Article
import com.example.newsapp.domain.usecase.news.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val  newsUseCases: NewsUseCases
): ViewModel() {

    var state by mutableStateOf(HomeUIState())

    val headlineNews = newsUseCases.getHeadlineNews(
        country = "us"
    ).cachedIn(viewModelScope)

    init {
        getCategoryNews(state.category)
    }
    fun onEvent(event: HomeUIEvents){

        when(event){
            is HomeUIEvents.OnCategoryChanged -> {
               state =  state.copy(
                    category = event.category
                )
                getCategoryNews(category = event.category)

            }
        }
    }

    private fun getCategoryNews(category: String){

        viewModelScope.launch(Dispatchers.IO){
            val response: Flow<PagingData<Article>> = newsUseCases.getCategoryNews(
                category = category,
                country = "us"
            )
            withContext(Dispatchers.Main){

                state = state.copy(
                    articles =response
                )
                Log.d("news"," category News ${response != null}")

            }
        }






    }

}