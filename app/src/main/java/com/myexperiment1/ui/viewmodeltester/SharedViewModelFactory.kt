package com.myexperiment1.ui.viewmodeltester

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.myexperiment1.data.network.PlaceHolderApiService
import java.lang.IllegalArgumentException

class SharedViewModelFactory(val placeHolderService:PlaceHolderApiService, ) :ViewModelProvider.Factory{
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SharedViewModel::class.java)){
            return SharedViewModel(placeHolderService) as T
        }
        throw IllegalArgumentException("Unknown View model class")
    }

}