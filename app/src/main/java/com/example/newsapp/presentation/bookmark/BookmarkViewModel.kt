package com.example.newsapp.presentation.bookmark

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.domain.model.Article
import com.example.newsapp.domain.usecase.news.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val useCases: NewsUseCases
): ViewModel()  {


    private val _state = mutableStateOf(BookmarkState())
    val state : State<BookmarkState> =_state


    init {
        getArticles()
    }
    private  fun getArticles(){

        try{
            useCases.selectArticles().onEach {

                _state.value = _state.value.copy(
                    articles = it.asReversed()
                )
            }.launchIn(viewModelScope)
        }catch (e:Exception){
            e.printStackTrace()
        }
    }



    // Function to toggle bookmark state of an article
    fun toggleBookmark(article: Article) {
        viewModelScope.launch {
            val isBookmarked = _state.value.bookmarkedArticles.contains(article)

            // If already bookmarked, remove it; otherwise, add it
            if (isBookmarked) {
                _state.value = _state.value.copy(
                    bookmarkedArticles = _state.value.bookmarkedArticles - article
                )
            } else {
                _state.value = _state.value.copy(
                    bookmarkedArticles = _state.value.bookmarkedArticles + article
                )
            }
        }
    }





    fun isBookmarked(article: Article): Boolean {
        return _state.value.bookmarkedArticles.contains(article)
    }




}