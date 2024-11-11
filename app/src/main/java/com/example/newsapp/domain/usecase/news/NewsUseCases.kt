package com.example.newsapp.domain.usecase.news


data class NewsUseCases(
    val getNews: GetNews,
    val searchNews: SearchNews,
    val insertArticle: InsertArticle,
    val deleteArticle: DeleteArticle,
    val selectArticles: SelectArticles,
    val selectArticle: Select_Article,
    val getCategoryNews: GetCategoryNews,
    val getHeadlineNews : GetHeadlineNews
)
