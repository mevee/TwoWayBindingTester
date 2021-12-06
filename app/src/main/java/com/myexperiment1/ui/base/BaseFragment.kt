package com.myexperiment1.ui.base

import android.widget.Toast
import androidx.fragment.app.Fragment

open class BaseFragment :Fragment(){


    fun printMessage(message:String){
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
    }
}