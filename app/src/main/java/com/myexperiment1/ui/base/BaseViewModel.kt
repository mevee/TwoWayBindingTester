package com.myexperiment1.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.myexperiment1.common.Atomic

open class BaseViewModel : ViewModel() {
    val isLoading = MutableLiveData<Atomic>()
    val errorMessage = MutableLiveData<String>()

    fun loading() {
         isLoading.postValue(Atomic(true))
    }

    fun completed() {
        isLoading.postValue(Atomic(false))
    }

    fun showError(message: String) {
        errorMessage.postValue(message)
    }

}