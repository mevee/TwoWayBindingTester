package com.myexperiment1.data.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.myexperiment1.BuildConfig
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance private constructor() {

    companion object {
        @Volatile
        var INSTANCE : Retrofit? = null

        fun getInstsnace () : PlaceHolderApiService? {
            //logging interceptor
            val httpLogging = HttpLoggingInterceptor()
            httpLogging.setLevel(HttpLoggingInterceptor.Level.BODY)
            val gson: Gson = GsonBuilder()
                .setLenient()
                .disableHtmlEscaping()
                .create()

            if (INSTANCE == null) {
                INSTANCE = Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
            }

            return INSTANCE?.create(PlaceHolderApiService::class.java)
        }

    }
}