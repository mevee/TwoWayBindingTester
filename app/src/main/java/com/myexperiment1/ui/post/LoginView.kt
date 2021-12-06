package com.myexperiment1.ui.post

interface LoginView {
    fun onError(message:String)
    fun onSuccess(data:String)
}