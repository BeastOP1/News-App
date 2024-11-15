package com.example.newsapp.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.domain.model.Article
import com.example.newsapp.domain.model.Source
import com.example.newsapp.domain.usecase.news.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases
) : ViewModel() {


    // Convert sideEffect to StateFlow
    private val _sideEffect = MutableStateFlow<String?>(null)
    val sideEffect: StateFlow<String?> = _sideEffect.asStateFlow()

    fun omEvent(event: DetailEvent) {
        when (event) {

            DetailEvent.RemoveSideEffect -> {
                _sideEffect.value = null
            }

            is DetailEvent.InsertDeleteArticle -> {
                viewModelScope.launch {

                    try {

                        val article = newsUseCases.selectArticle(event.article.url)

                        if (article == null) {
                            insertArticle(event.article)

                        } else {
                            deleteArticle(event.article)
                        }

                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }

            }

        }
    }

    private suspend fun deleteArticle(article: Article) {

        try {
            newsUseCases.deleteArticle(article) // Assuming deleteArticle exists in NewsUseCases
            _sideEffect.value = "Article Deleted"
        } catch (e: Exception) {
            e.printStackTrace()
            _sideEffect.value = "Error deleting article"
        }
    }

    private suspend fun insertArticle(article: Article) {
        try {
            newsUseCases.insertArticle(article) // Assuming insertArticle exists in NewsUseCases
            _sideEffect.value = "Article Saved"

        } catch (e: Exception) {
            e.printStackTrace()
            _sideEffect.value = "Error saving article"

        }
    }


}