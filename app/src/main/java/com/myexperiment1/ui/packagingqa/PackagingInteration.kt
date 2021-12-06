package com.myexperiment1.ui.packagingqa

import com.myexperiment1.model.resposnses.packagingqa.PackageItem

interface PackagingInteration {
    fun rePackaging(item:PackageItem)
    fun acceptPackaging(item:PackageItem)
}