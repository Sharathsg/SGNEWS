package com.example.sg_news_app.ViewModel

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sg_news_app.Models.Article
import com.example.sg_news_app.R
import com.example.sg_news_app.UI.Fragments.BusinessFragment
import com.example.sg_news_app.UI.Fragments.CricketFragment
import com.example.sg_news_app.UI.Fragments.EntertainmentFragment
import com.example.sg_news_app.UI.Fragments.NewsFargment
import com.example.sg_news_app.Util.ConnectivityUtil
import com.example.sg_news_app.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    @ApplicationContext val context: Context, private val mNewsRepository: NewsRepository
) : ViewModel() {
    var job: Job? = null
    val loading = MutableLiveData<Boolean>()
    val newsdetails = MutableLiveData<Article>()
    fun newsfeed(search: String, apikey: String) {

        if(ConnectivityUtil.isConnected(context)) {

            job = CoroutineScope(Dispatchers.IO).launch {
                val response = mNewsRepository.getnews(search, apikey)
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {

                          loading.postValue(true)
                        newsdetails.postValue(response.body())
                    } else {
                        loading.postValue(false)
                        Toast.makeText(
                            context,
                            context.getString(R.string.error_loading_msg),
                            Toast.LENGTH_SHORT
                        ).show()
                    }


                }
            }
        }else{
            loading.postValue(false)
            Toast.makeText(context, context.getString(R.string.no_internet),Toast.LENGTH_SHORT).show()


        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

    fun getNewsTypes(): List<Fragment> {
        return mutableListOf(
            NewsFargment(),
            CricketFragment(),
            EntertainmentFragment(),
            BusinessFragment(),
        )
    }

    fun getTabName(): Array<String> {
        val tabArray = arrayOf(
            context.getString(R.string.news_tabname),
            context.getString(R.string.cricket_tabname),
            context.getString(R.string.enterainment),
            context.getString(R.string.business_tabname)
        )
        return tabArray
    }

}