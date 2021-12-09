package com.myexperiment1.ui.viewmodeltester

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.myexperiment1.R
import com.myexperiment1.data.network.PlaceHolderApiService
import com.myexperiment1.data.network.RetrofitInstance
import com.myexperiment1.databinding.ActivityViewModeltesterBinding
import com.myexperiment1.ui.post.PostViewModel
import com.myexperiment1.ui.post.PostViewModelFactory

class ViewModeltesterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityViewModeltesterBinding
    val apiService: PlaceHolderApiService? by lazy {
        RetrofitInstance.getInstsnace()
    }
    lateinit var mainViewModel: SharedViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_view_modeltester)
        setupPostViewModel()
        loadFragmentA()
        loadFragmentB()
    }

    private fun setupPostViewModel() {
        mainViewModel = ViewModelProvider(this, SharedViewModelFactory(apiService!!))
            .get(SharedViewModel::class.java)
        binding.apply {
            viewModel = mainViewModel
        }
        Log.d("TAG---M", mainViewModel.toString())
        binding.setLifecycleOwner(this)
        mainViewModel.isLoading.observe(
            this, {
                Log.d("TAG---M", "isloding:${it}")
            }
        )
    }

    private fun loadFragmentA() {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.hostA, ProducerFragment.newInstance("", ""))
            commit()
        }
    }

    private fun loadFragmentB() {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.hostB, ConsumerFragment.newInstance("", ""))
            commit()
        }
    }


}