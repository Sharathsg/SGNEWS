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
import com.example.sg_news_app.databinding.FragmentBusinessBinding
import dagger.hilt.android.AndroidEntryPoint

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BusinessFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class BusinessFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var businessBinding: FragmentBusinessBinding
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
        businessBinding = FragmentBusinessBinding.inflate(layoutInflater, container, false)
        mNewsViewModel.newsfeed(getString(R.string.business), getString(R.string.api_key))
        CustomProgressDialog.showProgressDialog(context)
        setDataAdapter()
        businessBinding.businessRefresh.setOnRefreshListener {
            Refresh()
        }

        mNewsViewModel.loading.observe(viewLifecycleOwner,{
            if (it==false){
                CustomProgressDialog.hideProgressDialog()

            }
        })
        return businessBinding.root


    }

    fun Refresh() {
        mNewsViewModel.newsdetails.observe(requireActivity(), {
            newsAdapter = NewsAdapter(requireContext(), it.articles)
            businessBinding.businessRecyclerview.layoutManager =
                LinearLayoutManager(requireContext())
            businessBinding.businessRecyclerview.adapter = newsAdapter
            businessBinding.businessRefresh.isRefreshing = false

        })
    }

    fun setDataAdapter() {

        mNewsViewModel.newsdetails.observe(requireActivity(), {
            newsAdapter = NewsAdapter(requireContext(), it.articles)
            businessBinding.businessRecyclerview.layoutManager =
                LinearLayoutManager(requireContext())
            businessBinding.businessRecyclerview.adapter = newsAdapter
            CustomProgressDialog.hideProgressDialog()

        })
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BusinessFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BusinessFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

}