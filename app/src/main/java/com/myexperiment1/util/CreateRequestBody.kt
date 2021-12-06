package com.myexperiment1.util

import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody

object CreateRequestBody {

     val jsonMediaType: MediaType = "application/json".toMediaTypeOrNull()!!


    fun getCreatePostRequestBody(title: String,body: String,userId: String,): RequestBody {
        return (" title: \"$title\",\n" +
                "    body: \"$body\",\n" +
                "    userId: \"$userId\","
                ).toRequestBody(jsonMediaType)
    }
}