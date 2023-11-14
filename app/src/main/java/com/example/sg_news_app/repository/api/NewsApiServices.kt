package com.example.sg_news_app.repository.api

import androidx.lifecycle.LiveData
import com.example.sg_news_app.Models.Article
import com.example.sg_news_app.Models.Articles
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface NewsApiServices {


    @Headers("Content-Type: application/json")
    @GET("search")
    suspend fun getNewsDetails(@Query("q") searchdata: String,@Query("max") max:Int,@Query("apikey") apikey:String): Response<Article>


}