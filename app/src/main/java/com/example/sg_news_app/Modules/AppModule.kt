package com.example.sg_news_app.Modules

import com.example.sg_news_app.repository.api.NewsApiServices
import com.example.sg_news_app.repository.api.network.LiveDataCallAdapterFactoryForRetrofit
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule  {

    @Singleton
    @Provides
    fun getService(): NewsApiServices {
        return Retrofit.Builder()
            .baseUrl("https://gnews.io/api/v4/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactoryForRetrofit())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(getHttpLoggingInterceptor())
            .build()
            .create(NewsApiServices::class.java)
    }

    @Singleton
    @Provides
    fun getHttpLoggingInterceptor(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE)
        val httpClient = OkHttpClient.Builder()
            .cache(null)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
        httpClient.addInterceptor(httpLoggingInterceptor)
        return httpClient.build()
    }

}