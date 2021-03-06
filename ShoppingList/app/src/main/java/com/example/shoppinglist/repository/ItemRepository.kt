package com.example.shoppinglist.repository

import com.example.shoppinglist.api.RetrofitInstance
import com.example.shoppinglist.data.model.Item
import retrofit2.Response

class ItemRepository : ItemRepositoryInterface {
    override suspend fun getItem(
        date: String,
        name: String,
        quantity: Int,
        note: String
    ): Response<Item> {
        return RetrofitInstance.getApiService().getCurrentData(date, name, quantity, note)
    }
}