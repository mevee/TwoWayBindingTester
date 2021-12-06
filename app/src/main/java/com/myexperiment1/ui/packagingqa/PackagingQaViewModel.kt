package com.myexperiment1.ui.packagingqa

import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.Bindable
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myexperiment1.common.Atomic
import com.myexperiment1.data.network.PlaceHolderApiService
import com.myexperiment1.model.resposnses.packagingqa.PackageItem
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class PackagingQaViewModel(
    private val apiService: PlaceHolderApiService,
    val packagingInteration: PackagingInteration
) : ViewModel() {
    private val TAG = "PackagingQaViewModel"
    val packagingList = MutableLiveData<ArrayList<PackageItem>>()
    val changedList = MutableLiveData<Set<PackageItem>>()
    private val myList = mutableSetOf<PackageItem>()

    val isLoading = MutableLiveData(false)
    val isItemLoading = MutableLiveData(false)
    val isAllChangesDone = MutableLiveData(false)

    init {
        loadDummyList()
    }

    private fun loadDummyList() {
        viewModelScope.launch {
            isLoading.postValue(true)
            delay(300)
            isLoading.postValue(false)

            packagingList.postValue(
                arrayListOf(
                    PackageItem("11", "Chocolate", "Chocolote", "2", "1"),
                    PackageItem("12", "Test2", "Test2", "4", "5"),
                    PackageItem("14", "test4444", "test4444", "1", "2"),
                )
            )
        }

    }

    fun repPickPackage(item: PackageItem) {
//    hit api and updateListAccordingly and changedList
        Log.d(TAG, "rePackaging:${item.buId}")
        val isAlreadyAdded = myList.contains(item)
        if (!isAlreadyAdded) {
            myList.add(item)
        }
        changedList.postValue(myList)
    }

    fun acceptPackage(item: PackageItem) {
//    just accept and update changedList
        Log.d(TAG, "acceptPackaging:${item.buId}")
        item.isAccepted = true
        val isAlreadyAdded = myList.contains(item)
        if (!isAlreadyAdded) {
            myList.add(item)
        }
        packagingList.value.let {
            packagingList.postValue(it)
        }
        changedList.postValue(myList)
    }

    @BindingAdapter("android:visibility")
    fun getSubmitBtnVisibility(): Int {
        return if (isAllChangesDone.value!!) VISIBLE else GONE
    }

    @BindingAdapter("android:text")
    fun TextView.bindIntToString(value: Int?) {
        value?.let {
            setText(value.toString())
        }
    }

    @BindingAdapter("android:text")
    fun EditText.bindAnyToString(value: Any?) {
        value?.let {
            setText(value.toString())
        }
    }
}