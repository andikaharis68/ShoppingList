package com.example.shoppinglist.api

import com.example.shoppinglist.data.model.Item
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ServiceApi {
    @GET("users")
    suspend fun getCurrentData(
            @Query("date") date: String,
            @Query("name") name: String,
            @Query("quantity") quantity: Int,
            @Query("note") note: String
    ): Response<Item>

//    @POST()
}