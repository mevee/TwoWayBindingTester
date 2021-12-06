package com.myexperiment1.model.resposnses

data class Form(val id:Int,
                var q1:String,
                var q2:String,
){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Form)
            return false
        else {
            if (other.id == this.id)
                return true
        }
        return false
    }
}