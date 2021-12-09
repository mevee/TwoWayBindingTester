package com.myexperiment1.ui.viewmodeltester

import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.Bindable
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myexperiment1.common.Atomic
import com.myexperiment1.data.network.PlaceHolderApiService
import com.myexperiment1.model.resposnses.packagingqa.PackageItem
import com.myexperiment1.ui.packagingqa.PackagingInteration
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SharedViewModel(
    private val apiService: PlaceHolderApiService,
) : ViewModel() {
    private val TAG = "PackagingQaViewModel"
    val packagingList = MutableLiveData<ArrayList<PackageItem>>()
    val changedList = MutableLiveData<Set<PackageItem>>()

    val liveText = MediatorLiveData<String>()
    var isLoading = MutableLiveData(false)
    val isItemLoading = MutableLiveData(false)
    val isAllChangesDone = MutableLiveData(false)

    init {
        loadDummyList()
    }

    private fun loadDummyList() {
        viewModelScope.launch {
            isLoading.postValue(true)
            delay(400)
//            liveText.value ="Auto initilizer"
            liveText.postValue("Auto initilizer")

            isLoading.postValue(false)

        }
    }
     fun loadRandom() {
        viewModelScope.launch {
            isLoading.postValue(true)
            delay(400)
             liveText.postValue("Random initilizer")
            isLoading.postValue(false)

        }
    }
}