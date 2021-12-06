package com.myexperiment1.model.resposnses.packagingqa

data class PackageItem(
    val buId: String,
    var supplimentLineNum: String,
    val description: String,
    val pickedQt: String,
    var packedQty: String,
    var isAccepted: Boolean =false,
)