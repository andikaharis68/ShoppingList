package com.example.shoppinglist.repository

import com.example.shoppinglist.api.ItemApi
import com.example.shoppinglist.data.model.Item
import com.example.shoppinglist.data.model.ItemRequest
import retrofit2.Response
import javax.inject.Inject


class ItemRepository @Inject constructor(val itemApi: ItemApi) : ItemRepositoryInterface{

    override suspend fun getAllItem(): Response<List<Item>> {
        return itemApi.getData()
    }

    override suspend fun getItemById(id: Int): Response<Item> {
        return itemApi.findById(id)
    }

    override suspend fun addItem(request: ItemRequest): Response<Item> {
        return itemApi.addData(request)
    }

    override suspend fun deleteItem(id: Int): Response<Item> {
        return itemApi.delete(id)
    }

    override suspend fun editItem(request: ItemRequest): Response<Item> {
        return itemApi.editById(request.id, request)
    }
}