package com.example.newsapp.presentation.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.domain.model.Article
import com.example.newsapp.domain.usecase.news.NewsUseCases
import com.example.newsapp.presentation.bookmark.BookmarkViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases
): ViewModel(){

//    var sideEffect by mutableStateOf<String?>(null )
//        private set

    // Convert sideEffect to StateFlow
    private val _sideEffect = MutableStateFlow<String?>(null)
    val sideEffect: StateFlow<String?> = _sideEffect.asStateFlow()


    fun omEvent(event: DetailEvent){
        when(event){

            DetailEvent.RemoveSideEffect -> {
                _sideEffect.value = null
            }
            is DetailEvent.InsertDeleteArticle ->{
                viewModelScope.launch {

                    try {

                        val article = newsUseCases.selectArticle(url = event.article.url)

                        if(article == null){
                            insertArticle(event.article)
                        }else{
                            deleteArticle(event.article)
                        }


                    }catch (e: Exception){
                        e.printStackTrace()
                    }
                }

            }

        }
    }

    private  suspend fun deleteArticle(article: Article) {
//        newsUseCases.insertArticle(article = article)
//        sideEffect = "Article Deleted"
//        newsUseCases.selectArticles
//
//

        try {
            newsUseCases.deleteArticle(article) // Assuming deleteArticle exists in NewsUseCases
            _sideEffect.value = "Article Deleted"
        } catch (e: Exception) {
            e.printStackTrace()
            _sideEffect.value = "Error deleting article"
        }
    }

    private suspend fun insertArticle(article: Article) {
//        newsUseCases.insertArticle(article = article)
//        sideEffect = "Article Saved"
//        newsUseCases.selectArticles

        try {
            newsUseCases.insertArticle(article) // Assuming insertArticle exists in NewsUseCases
            _sideEffect.value = "Article Saved"
        } catch (e: Exception) {
            e.printStackTrace()
            _sideEffect.value = "Error saving article"
            
        }
    }



}