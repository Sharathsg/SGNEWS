package com.example.sg_news_app.Models

import com.google.gson.annotations.SerializedName

data class Source(
    @SerializedName("name" ) var name : String? = null,
    @SerializedName("url"  ) var url  : String? = null
)
