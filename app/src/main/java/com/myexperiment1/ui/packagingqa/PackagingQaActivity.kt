package com.myexperiment1.ui.packagingqa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.myexperiment1.R
import com.myexperiment1.common.Atomic
import com.myexperiment1.data.network.PlaceHolderApiService
import com.myexperiment1.data.network.RetrofitInstance
import com.myexperiment1.databinding.ActivityPackigingQaBinding
import com.myexperiment1.model.resposnses.packagingqa.PackageItem
import com.myexperiment1.ui.post.PostViewModel
import com.myexperiment1.ui.post.PostViewModelFactory

class PackagingQaActivity : AppCompatActivity(), PackagingInteration {
    private val TAG = "PackagingQaActivity"
    lateinit var binding: ActivityPackigingQaBinding
    lateinit var viewModel: PackagingQaViewModel
    lateinit var apiService: PlaceHolderApiService
    private lateinit var packagingAdapter: PackagingQaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_packiging_qa)
        binding.back.setOnClickListener { onBackPressed() }
        supportActionBar?.hide()
        apiService = RetrofitInstance.getInstsnace()!!
        setupPostViewModel()
        setDataObservers()

    }

    private fun setDataObservers() {
        packagingAdapter = PackagingQaAdapter(this, viewModel)
        binding.rvPackingItemList.apply {
            adapter = packagingAdapter
        }

        viewModel.packagingList.observe(this, {
            packagingAdapter.submitList(it)
        })
        viewModel.changedList.observe(this, {
            val isAllChangesDone = it.size == viewModel.packagingList.value?.size
            if (isAllChangesDone)
            viewModel.isAllChangesDone.postValue(isAllChangesDone)

        })

    }

    private fun setupPostViewModel() {
        viewModel = ViewModelProvider(this, PackagingQaViewModelFactory(apiService, this))
            .get(PackagingQaViewModel::class.java)
        binding.mViewModel = viewModel
    }

    override fun rePackaging(item: PackageItem) {
        viewModel.repPickPackage(item)
    }

    override fun acceptPackaging(item: PackageItem) {
        viewModel.acceptPackage(item)
    }
}