package com.myexperiment1.model.resposnses

data class Post(val id:Int,
                var title:String,
                val body:String,
                val userId:Int?,
                var newTitle:String,
                var isEditModeEnabled:Boolean=false,
)