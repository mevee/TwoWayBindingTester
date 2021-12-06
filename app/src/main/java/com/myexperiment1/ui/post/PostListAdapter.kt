package com.myexperiment1.ui.post

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.myexperiment1.R
import com.myexperiment1.databinding.ItemPostLayoutBinding
import com.myexperiment1.model.resposnses.Post

class PostListAdapter(val context: Context,val viewModel: PostViewModel) :
    RecyclerView.Adapter<PostListAdapter.PostViewHolder>() {
    var dataList: List<Post>? = null

    fun submitList(postList: List<Post>) {
        dataList = postList
        dataList?.forEach {
            it.newTitle =it.title
        }
        notifyDataSetChanged()
    }

    inner class PostViewHolder(
        val binding: ItemPostLayoutBinding,
        var isEditModeEnabled: Boolean = false
    ) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = ItemPostLayoutBinding.inflate(LayoutInflater.from(context), parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val item = dataList!![position]
        val binding = holder.binding
        binding.post = item
//        binding.teTitle.isVisible = item.isEditModeEnabled
//        binding.btnChangeTitle.isVisible = item.isEditModeEnabled
        /*
        if (holder.isEditModeEnabled) {

        } else {
            binding.teTitle.isVisible = false
            binding.btnChange.isVisible = false
        }
*/
        binding.setOnItemClick {
            when (it.id) {
//                R.id.ivEdit -> {
//                    item.isEditModeEnabled = !item.isEditModeEnabled
                   /* notifyItemChanged(position)
                }
                R.id.btnChangeTitle -> {
                    if (item.title != item.newTitle)
                        changeTitleOfItem(item,position)
                    else
                        Toast.makeText(context,"Title not changed",Toast.LENGTH_LONG).show()
                }*/
            }
        }
    }

    private fun changeTitleOfItem(item: Post, position: Int) {
//        sending items to server
//        and replacing the item title with newtitle
//        viewModel.updateExistingPostItem(post = item)
        item.title=item.newTitle
        item.isEditModeEnabled=false
        notifyItemChanged(position)
    }


    override fun getItemCount(): Int {
        return if (dataList == null) 0 else dataList!!.size
    }


    fun upDateTitleAtPostion(position: Int) {
        dataList!![position].title = ""
    }
}