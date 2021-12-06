package com.myexperiment1.ui.post

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.myexperiment1.R
import com.myexperiment1.data.network.PlaceHolderApiService
import com.myexperiment1.data.network.RetrofitInstance
import com.myexperiment1.databinding.FragmentAllPostBinding
import com.myexperiment1.ui.base.BaseFragment

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class AllPostFragment : BaseFragment() {

    private var param1: String? = null
    private var param2: String? = null
    lateinit var viewModel: PostViewModel
    private lateinit var postAdapter: PostListAdapter
    private lateinit var binding: FragmentAllPostBinding
    val apiService: PlaceHolderApiService? by lazy {
        RetrofitInstance.getInstsnace()
    }

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
        binding =  FragmentAllPostBinding.inflate(inflater, container, false)
        setupPostViewModel()
        setPostLisView()
        setupObservers()

        return binding.root
    }

    private fun setupObservers() {

        viewModel.postList.observe(viewLifecycleOwner, {
//            it.forEach { Log.d(TAG, "${it.title}") }
            if (it.size>0)
                postAdapter.submitList(it)
        }
        )
    }
    private fun setPostLisView() {
        postAdapter = PostListAdapter(requireContext(),viewModel)

        binding.rvPostList.apply {
            layoutManager =LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
            adapter = postAdapter
        }

    }

    private fun setupPostViewModel() {
        viewModel = ViewModelProvider(requireActivity(), PostViewModelFactory(apiService!!))
            .get(PostViewModel::class.java)
    }

    companion object {
        val TAG = "PostActivity"

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AllPostFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}