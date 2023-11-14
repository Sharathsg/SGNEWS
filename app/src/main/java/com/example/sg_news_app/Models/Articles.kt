package com.example.sg_news_app.Models

import com.google.gson.annotations.SerializedName

data class Articles(

    @SerializedName("title"       ) var title       : String? = null,
    @SerializedName("description" ) var description : String? = null,
    @SerializedName("content"     ) var content     : String? = null,
    @SerializedName("url"         ) var url         : String? = null,
    @SerializedName("image"       ) var image       : String? = null,
    @SerializedName("publishedAt" ) var publishedAt : String? = null,
    @SerializedName("source"      ) var source      : Source? = Source()
)
