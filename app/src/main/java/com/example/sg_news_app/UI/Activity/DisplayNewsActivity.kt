package com.example.sg_news_app.UI.Activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.sg_news_app.R
import com.example.sg_news_app.databinding.ActivityDisplayNewsBinding

class DisplayNewsActivity : AppCompatActivity() {
    private lateinit var displayNewsBinding: ActivityDisplayNewsBinding
    lateinit var Image: String
    lateinit var contentdata: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        displayNewsBinding = ActivityDisplayNewsBinding.inflate(layoutInflater)
        setContentView(displayNewsBinding.root)
        val extras = intent.extras
        Image = extras?.getString(getString(R.string.news_image)).toString()
        contentdata = extras?.getString(getString(R.string.news_content)).toString()
        title = extras?.getString(getString(R.string.news_details_title)).toString()


        Glide.with(this).load(Image).into(displayNewsBinding.imageCollapse);
        displayNewsBinding.contentData.text = contentdata
        displayNewsBinding.titleData.text= title


    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}