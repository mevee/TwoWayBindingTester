package com.myexperiment1.ui.post

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.myexperiment1.R
import com.myexperiment1.data.network.PlaceHolderApiService
import com.myexperiment1.data.network.RetrofitInstance
import com.myexperiment1.databinding.ActivityPostBinding
import com.myexperiment1.ui.packagingqa.PackagingQaActivity
import com.myexperiment1.ui.post.formSubmit.FormSubmitFragment

class PostActivity : AppCompatActivity() {
    private val TAG = "PostActivity"
    lateinit var binding: ActivityPostBinding
    lateinit var viewModel: PostViewModel
    val apiService: PlaceHolderApiService? by lazy {
        RetrofitInstance.getInstsnace()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_post)
//        loadPostFragment()
        startActivity(Intent(this,PackagingQaActivity::class.java))
        finish()
        loadFormList()
        setupPostViewModel()
        setupObservers()

    }

    private fun loadPostFragment() {
        val fragmentPostList = AllPostFragment.newInstance("", "")
        supportFragmentManager.beginTransaction()
            .replace(R.id.hostFragment, fragmentPostList, AllPostFragment.TAG).commit()
    }
    private fun loadFormList() {
        val fragmentPostList = FormSubmitFragment.newInstance("", "")
        supportFragmentManager.beginTransaction()
            .replace(R.id.hostFragment, fragmentPostList, AllPostFragment.TAG).commit()
    }

    private fun setupObservers() {
        viewModel?.isLoading.observe(this, {
            binding.progressBar.isVisible = it.isTrue
        }
        )
    }

    private fun setupPostViewModel() {
        viewModel = ViewModelProvider(this, PostViewModelFactory(apiService!!))
            .get(PostViewModel::class.java)
    }
}