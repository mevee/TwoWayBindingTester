package com.myexperiment1.ui.packagingqa

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.myexperiment1.data.network.PlaceHolderApiService
import java.lang.IllegalArgumentException

class PackagingQaViewModelFactory(val placeHolderService:PlaceHolderApiService,
                                  val packingInteration:PackagingInteration ) :ViewModelProvider.Factory{
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(PackagingQaViewModel::class.java)){
            return PackagingQaViewModel(placeHolderService,packingInteration) as T
        }
        throw IllegalArgumentException("Unknown View model class")
    }

}