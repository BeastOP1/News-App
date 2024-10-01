package com.example.newsapp.di

import android.app.Application
import androidx.room.Room
import com.example.newsapp.data.local.NewsDao
import com.example.newsapp.data.local.NewsDataBase
import com.example.newsapp.data.local.NewsTypeConverter
import com.example.newsapp.data.manger.LocalUserMangerIMP
import com.example.newsapp.data.remote.NewsApi
import com.example.newsapp.data.repository.NewsRepositoryImpl
import com.example.newsapp.domain.manger.LocalUserManger
import com.example.newsapp.domain.repository.NewsRepository
import com.example.newsapp.domain.usecase.news.NewsUseCases
import com.example.newsapp.domain.usecase.app_entry.AppEntryUseCases
import com.example.newsapp.domain.usecase.app_entry.ReadAppEntry
import com.example.newsapp.domain.usecase.app_entry.SaveAppEntry
import com.example.newsapp.domain.usecase.news.DeleteArticle
import com.example.newsapp.domain.usecase.news.GetNews
import com.example.newsapp.domain.usecase.news.InsertArticle
import com.example.newsapp.domain.usecase.news.Select_Article
import com.example.newsapp.domain.usecase.news.SearchNews
import com.example.newsapp.domain.usecase.news.SelectArticles
import com.example.newsapp.ui.theme.utils.Constants.BASE_URL
import com.example.newsapp.ui.theme.utils.Constants.NEWS_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManger(
        application: Application
    ):LocalUserManger {
        return LocalUserMangerIMP(application)
    }

    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManger: LocalUserManger
    ) = AppEntryUseCases(
            readAppEntry = ReadAppEntry(localUserManger = localUserManger),
            saveAppEntry = SaveAppEntry(localUserManger = localUserManger)
        )


    @Provides
    @Singleton
    fun provideNewsApi():NewsApi{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(
        newsApi: NewsApi,
        newsDao: NewsDao
    ):NewsRepository = NewsRepositoryImpl(newsApi, newsDao)

    @Provides
    @Singleton
    fun provideNewsUseCases(newsRepository: NewsRepository,
                            newsDao: NewsDao): NewsUseCases {
        return NewsUseCases(
            getNews = GetNews(newsRepository),
            searchNews = SearchNews(newsRepository),
            insertArticle = InsertArticle(newsRepository = newsRepository),
            deleteArticle = DeleteArticle(newsRepository = newsRepository),
            selectArticles = SelectArticles(newsRepository = newsRepository),
            selectArticle = Select_Article(newsRepository = newsRepository)
        )
    }


    @Singleton
    @Provides
    fun provideNewsDatabase(
        application: Application
    ): NewsDataBase{
        return Room.databaseBuilder(
            context = application,
            klass = NewsDataBase::class.java,
            name = NEWS_DATABASE_NAME
        ).addTypeConverter(NewsTypeConverter())
            .fallbackToDestructiveMigration()
            .build()
    }


    @Singleton
    @Provides
    fun provideNewsDao(
        newsDataBase: NewsDataBase
    ): NewsDao{
        return newsDataBase.newsDao
    }

}