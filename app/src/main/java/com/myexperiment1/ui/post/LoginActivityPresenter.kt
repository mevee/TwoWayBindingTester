package com.myexperiment1.ui.post

import androidx.lifecycle.MutableLiveData
import com.myexperiment1.ui.base.BasePresenter

class LoginActivityPresenter(
    val loginView: LoginView
) : BasePresenter() {


    val listOfDuties=MutableLiveData<List<String>>()
//    val repository: ApiService = TODO()
//    init {
//        model =object ApiService{
//         override fun getAllNames(){
//
//         }
//        }
//    }

    fun login(email: String, password: String) {
        loading()
//        model.login(email, password)
        loginView.onSuccess("$email and $password")
//        completed()
    }

    fun loadAllNames() {
        loading()
//        repository.getAllNames()
        completed()
    }

    fun addAllNames(notes: List<String>) {
        loading()
//        repository.getAllNames()
        completed()
    }

}
