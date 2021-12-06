package com.myexperiment1.ui.packagingqa

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.myexperiment1.R
import com.myexperiment1.databinding.ItemPackedqaLayoutBinding
import com.myexperiment1.databinding.ItemPostLayoutBinding
import com.myexperiment1.model.resposnses.Post
import com.myexperiment1.model.resposnses.packagingqa.PackageItem

class PackagingQaAdapter(val context: Context, val viewModel: PackagingQaViewModel) :
    RecyclerView.Adapter<PackagingQaAdapter.PostViewHolder>() {
    var dataList: List<PackageItem>? = null

    fun submitList(postList: List<PackageItem>) {
        dataList = postList
        notifyDataSetChanged()
    }

    inner class PostViewHolder(
        val binding: ItemPackedqaLayoutBinding,
    ) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = ItemPackedqaLayoutBinding.inflate(LayoutInflater.from(context), parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val item = dataList!![position]
        val binding = holder.binding
        binding.packagingQa = item
        binding.mViewModel = viewModel
    }

    override fun getItemCount(): Int {
        return if (dataList == null) 0 else dataList!!.size
    }

}