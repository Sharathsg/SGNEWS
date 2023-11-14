package com.example.sg_news_app.repository

import android.content.Context
import com.example.sg_news_app.App.AppExecutors
import com.example.sg_news_app.Models.Article
import com.example.sg_news_app.repository.api.NewsApiServices
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class NewsRepository @Inject constructor(private val apiServices: NewsApiServices,
                                         @ApplicationContext val context: Context,
                                         private val appExecutors: AppExecutors = AppExecutors()
) {

    suspend fun getnews(search: String ,apikey:String): Response<Article> {
        return apiServices.getNewsDetails(search,20,apikey,)
    }





}