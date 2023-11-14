package com.example.sg_news_app.UI.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sg_news_app.Adapter.NewsAdapter
import com.example.sg_news_app.R
import com.example.sg_news_app.Util.CustomProgressDialog
import com.example.sg_news_app.ViewModel.NewsViewModel
import com.example.sg_news_app.databinding.FragmentEntertainmentBinding
import dagger.hilt.android.AndroidEntryPoint

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [EntertainmentFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class EntertainmentFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var entertainmentBinding: FragmentEntertainmentBinding
    private val mNewsViewModel: NewsViewModel by viewModels()
    private lateinit var newsAdapter: NewsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        entertainmentBinding =
            FragmentEntertainmentBinding.inflate(layoutInflater, container, false)
        mNewsViewModel.newsfeed(getString(R.string.entertainment), getString(R.string.api_key))
        CustomProgressDialog.showProgressDialog(context)
        setDataAdapter()

        entertainmentBinding.entertainmentRefresh.setOnRefreshListener {

            refresh()
        }
        mNewsViewModel.loading.observe(requireActivity(),{
            if (it==false){
                CustomProgressDialog.hideProgressDialog()

            }
        })
        return entertainmentBinding.root
    }


    fun setDataAdapter() {

        mNewsViewModel.newsdetails.observe(requireActivity(), {
            newsAdapter = NewsAdapter(requireContext(), it.articles)
            entertainmentBinding.entertainmentRecyclerview.layoutManager =
                LinearLayoutManager(requireContext())
            entertainmentBinding.entertainmentRecyclerview.adapter = newsAdapter
            CustomProgressDialog.hideProgressDialog()

        })


    }

    fun refresh() {
        mNewsViewModel.newsdetails.observe(requireActivity(), {
            newsAdapter = NewsAdapter(requireContext(), it.articles)
            entertainmentBinding.entertainmentRecyclerview.layoutManager =
                LinearLayoutManager(requireContext())
            entertainmentBinding.entertainmentRecyclerview.adapter = newsAdapter
            entertainmentBinding.entertainmentRefresh.isRefreshing = false
        })


    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment EntertainmentFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            EntertainmentFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


}