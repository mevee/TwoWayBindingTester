package com.myexperiment1.ui.post.formSubmit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.myexperiment1.R
import com.myexperiment1.common.Atomic
import com.myexperiment1.data.network.PlaceHolderApiService
import com.myexperiment1.data.network.RetrofitInstance
import com.myexperiment1.databinding.FragmentFormSubmitBinding
import com.myexperiment1.model.resposnses.Form
import com.myexperiment1.ui.base.BaseFragment
import com.myexperiment1.ui.post.PostViewModel
import com.myexperiment1.ui.post.PostViewModelFactory

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class FormSubmitFragment : BaseFragment() {
    private var param1: String? = null
    private var param2: String? = null
    lateinit var binding: FragmentFormSubmitBinding
    lateinit var viewModel: PostViewModel
    val apiService: PlaceHolderApiService? by lazy {
        RetrofitInstance.getInstsnace()
    }
    private lateinit var postAdapter: FormListAdapter

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
        binding = FragmentFormSubmitBinding.inflate(inflater, container, false)

        setupPostViewModel()
        setPostLisView()
        setupObservers()
        setupClickLisner()

        return binding.root
    }

    private fun setupClickLisner() {
        /*binding.setOnItemClick {
            when (it.id) {
                R.id.btnSave -> {
                viewModel.submitChangedList()
                }
            }
        }*/
    }

    private fun setupObservers() {

        viewModel.errorMessage.observe(viewLifecycleOwner, {
            if (it.isNotEmpty())
                printMessage(it)
        })

        viewModel?.formList.observe(viewLifecycleOwner, {
            postAdapter.submitList(it)
//            checkForValidFormInput(it)
        })

        viewModel?.changedFormList.observe(viewLifecycleOwner, {
            if (it.size > 0)
                viewModel?.isFormCorrect.value = Atomic(true)
            else
                viewModel?.isFormCorrect.value = Atomic(false)

        })

        viewModel?.isFormCorrect.observe(viewLifecycleOwner, {
            if (it.isTrue) {
                binding.btnSave.alpha = 1f
                binding.btnSave.isEnabled = true
            } else {
                binding.btnSave.alpha = .5f
                binding.btnSave.isEnabled = false
            }
        })
    }

    fun checkForValidFormInput(dataList: List<Form>) {
        dataList?.forEach {
            if (it.q1.isNullOrEmpty() && it.q2.isNullOrEmpty()) {
                viewModel.isFormCorrect.value = Atomic(false)
            } else if (it.q1.toInt() >= it.q2.toInt()) {
                viewModel.isFormCorrect.value = Atomic(false)
            } else {
                viewModel.isFormCorrect.value = Atomic(true)
            }
        }
    }
    private fun setPostLisView() {
        postAdapter = FormListAdapter(requireContext(), viewModel)
        binding.rvList.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = postAdapter
        }

    }

    private fun setupPostViewModel() {
        viewModel = ViewModelProvider(requireActivity(), PostViewModelFactory(apiService!!))
            .get(PostViewModel::class.java)
        binding.viewModel =viewModel
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FormSubmitFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}