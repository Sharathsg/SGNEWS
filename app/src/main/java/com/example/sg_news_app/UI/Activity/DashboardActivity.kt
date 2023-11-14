package com.example.sg_news_app.UI.Activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.sg_news_app.Adapter.AdapterViewPager
import com.example.sg_news_app.Adapter.NewsAdapter
import com.example.sg_news_app.App.Myapp
import com.example.sg_news_app.ViewModel.NewsViewModel
import com.example.sg_news_app.databinding.ActivityDashboardBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDashboardBinding
    private val mNewsViewModel: NewsViewModel by viewModels()
    private lateinit var newsAdapter: NewsAdapter
    private lateinit var fragmentList: List<Fragment>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Myapp.setAppContext(baseContext)
        fragmentList = mNewsViewModel.getNewsTypes()
        setViewPager()
        binding.newsViewpager.isUserInputEnabled = true
        TabLayoutMediator(binding.tabLayout, binding.newsViewpager) { tab, position ->
            tab.text = mNewsViewModel.getTabName()[position]
        }.attach()


    }

    fun setViewPager() {
        val adapterViewPager = AdapterViewPager(this, fragmentList)
        binding.newsViewpager.adapter = adapterViewPager

        binding.newsViewpager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                when (position) {
                }
            }
        })


    }
}