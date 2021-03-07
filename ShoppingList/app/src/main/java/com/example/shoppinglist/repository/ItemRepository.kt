package com.example.shoppinglist.repository

import com.example.shoppinglist.api.RetrofitInstance
import com.example.shoppinglist.data.model.Item
import com.example.shoppinglist.data.model.ItemRequest
import retrofit2.Response


class ItemRepository: ItemRepositoryInterface {

    override suspend fun getAllItem(): Response<List<Item>> {
        return RetrofitInstance.getApiService().getData()
    }

    override suspend fun getItemById(id: Int): Response<Item> {
        return RetrofitInstance.getApiService().findById(id)
    }

    override suspend fun addItem(request: ItemRequest): Response<Item> {
        return RetrofitInstance.getApiService().addData(request)
    }

    override suspend fun deleteItem(id: Int): Response<Item> {
        return RetrofitInstance.getApiService().delete(id)
    }

    override suspend fun editItem(request: ItemRequest): Response<Item> {
        return RetrofitInstance.getApiService().editById(request.id, request)
    }
}