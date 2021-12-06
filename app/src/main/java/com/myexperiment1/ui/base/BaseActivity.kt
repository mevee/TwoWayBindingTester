package com.myexperiment1.ui.base

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity:AppCompatActivity() {

    fun printMessage(message:String){
        Toast.makeText(this,message, Toast.LENGTH_LONG).show()
    }
}