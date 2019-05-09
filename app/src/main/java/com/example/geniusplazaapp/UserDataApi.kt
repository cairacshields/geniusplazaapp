package com.example.geniusplazaapp

import android.content.Context
import retrofit2.Retrofit
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory



class UserDataApi {

    private val BASE_URL = "https://reqres.in/api/users/"

    private var retrofit: Retrofit? = null

    private fun getClient(context: Context): Retrofit {

        if(retrofit == null){
            retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build()
        }

        return retrofit!!
    }

}