package com.myexperiment1.ui.post.formSubmit

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.myexperiment1.R
import com.myexperiment1.common.Atomic
import com.myexperiment1.databinding.ItemFormLayoutBinding
import com.myexperiment1.databinding.ItemPostLayoutBinding
import com.myexperiment1.model.resposnses.Form
import com.myexperiment1.model.resposnses.Post
import com.myexperiment1.ui.post.PostViewModel

class FormListAdapter(val context: Context, val viewModel: PostViewModel) :
    RecyclerView.Adapter<FormListAdapter.PostViewHolder>() {
    var dataList: List<Form>? = null

    fun submitList(postList: List<Form>) {
        dataList = postList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = ItemFormLayoutBinding.inflate(LayoutInflater.from(context), parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val item = dataList!![position]
        val binding = holder.binding
        binding.post = item
        binding.viewModel = viewModel
    }

    override fun getItemCount(): Int {
        return if (dataList == null) 0 else dataList!!.size
    }

    inner class PostViewHolder(val binding: ItemFormLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)
}