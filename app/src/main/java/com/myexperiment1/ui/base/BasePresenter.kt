package com.myexperiment1.ui.base

import androidx.lifecycle.MutableLiveData
import com.myexperiment1.common.Atomic

open class BasePresenter {
    val isLoading = MutableLiveData<Atomic>()

    fun loading(){
        isLoading.value =Atomic(true)
    }
    fun completed(){
        isLoading.value =Atomic(false)
    }

}