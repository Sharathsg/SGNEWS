package com.example.sg_news_app.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sg_news_app.Models.Articles
import com.example.sg_news_app.R
import com.example.sg_news_app.UI.Activity.DisplayNewsActivity
import com.example.sg_news_app.databinding.NewsLayoutBinding


class NewsAdapter(
    private val context: Context, private val newsval: ArrayList<Articles>

) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    // added this here, so you're passing it in at construction

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = NewsLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }


    inner class ViewHolder(val binding: NewsLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Glide.with(context).load(newsval.get(position).image).into(holder.binding.bannerImage);

        holder.binding.textData.text = newsval.get(position).title

        holder.itemView.setOnClickListener {





            val intent = Intent(context, DisplayNewsActivity::class.java)
            intent.putExtra(context.getString(R.string.news_image), newsval.get(position).image)
            intent.putExtra(context.getString(R.string.news_content), newsval.get(position).content)
            intent.putExtra(context.getString(R.string.news_details_title),newsval.get(position).title)
            context.startActivity(intent)

        }

    }


    override fun getItemCount(): Int {

        return newsval.size
    }


    interface DisplayFullData {

        fun displayData(newsval: Articles)
    }
}