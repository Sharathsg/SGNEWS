package com.example.sg_news_app.App

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class Myapp : Application() {

    companion object {
        var context: Context? = null

        fun setAppContext(appContext: Context) {
            context = appContext
        }

        fun getAppContext(): Context {
            return context!!
        }
    }
}