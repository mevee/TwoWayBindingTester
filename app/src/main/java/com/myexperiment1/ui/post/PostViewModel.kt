package com.myexperiment1.ui.post

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.myexperiment1.common.Atomic
import com.myexperiment1.data.network.PlaceHolderApiService
import com.myexperiment1.model.resposnses.Form
import com.myexperiment1.model.resposnses.Post
import com.myexperiment1.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class PostViewModel(private val placeHolderService: PlaceHolderApiService) : BaseViewModel() {
    val TAG = "PostViewModel"
    val postList = MutableLiveData<ArrayList<Post>>()
    val formList = MutableLiveData<ArrayList<Form>>()
    val changedFormList = MutableLiveData<MutableSet<Form>>()
    val isFormCorrect = MutableLiveData(Atomic(false))

    init {
        changedFormList.postValue(mutableSetOf())
//        loadAllPosts()
        addDummyDataToForm()

    }

    private fun loadAllPosts() {
        loading()
        viewModelScope.launch(Dispatchers.IO) {
            val postResponse = placeHolderService.getAllPost()
            if (postResponse.isSuccessful) {
                completed()
                val dataList = postResponse.body()
                dataList?.let {
                    postList.postValue(it)
                }
            } else {
                completed()

            }
        }

    }

    private fun addDummyDataToForm() {
        loading()
        viewModelScope.launch(Dispatchers.IO) {
            delay(300)
            val list = ArrayList<Form>()
            list.add(Form(1, "100", "100"))
            list.add(Form(2, "200", "100"))
            list.add(Form(3, "300", "300"))
            list.add(Form(4, "400", "500"))
            list.add(Form(5, "500", "500"))
            formList.postValue(list)
            completed()
        }
    }

    fun submitItem(item1: Form) {
        if (item1.q1.isEmpty() && item1.q2.isEmpty()) {
            showError("Please enter both Q1 and Q2")
        } else if (item1.q1.toInt() >= item1.q2.toInt()) {
            showError(" Q1 should be less then Q2")
        } else {
            if (changedFormList.value != null) {
                if (changedFormList.value!!.add(item1)) {
                    showError("Added")
                } else {
                    showError("Already added")
                }
            }
        }
    }

    fun onQ2TextChanged(newText: String, item1: Form) {
        viewModelScope.launch {
            delay(60)
            if (item1.q1.isEmpty() && item1.q2.isEmpty()) {
                showError("Please enter both Q1 and Q2")
            } else if (item1.q1.toInt() >= item1.q2.toInt()) {
                showError(" Q1 should be less then Q2")
                Log.d(TAG, "${newText}ID" + item1.id)

            } else {
                val selectdLIst = changedFormList.value
                if (selectdLIst != null) {
                    if (selectdLIst.add(item1)) {
//                        showError("Added")
                        changedFormList.postValue(selectdLIst!!)
                    } else {
//                        showError("Already added")
                    }
                }
            }
        }
    }

    fun submitChangedList() {
        changedFormList.value?.let {
            it.forEach {
                Log.d(TAG, "Sending .." + it.toString())
            }
        }
        showError("All List sent to server hurray............")
    }
}