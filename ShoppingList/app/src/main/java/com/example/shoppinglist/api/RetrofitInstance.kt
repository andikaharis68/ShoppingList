package com.example.shoppinglist.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("10.0.2.2:3000/")
            .build()
    fun getApiService() = retrofit.create(ServiceApi::class.java)
}