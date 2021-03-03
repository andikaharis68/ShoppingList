package com.example.shoppinglist.database.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.shoppinglist.data.model.Item

interface ItemRepositoryInterface {
    val itemsList: MutableLiveData<List<Item>>
    fun findItemByIdRepository(id:Int): MutableLiveData<Item>
    suspend fun addItemRepository(item : Item)
    suspend fun updateItemRepository(item : Item)
    suspend fun deleteItemRepository(user: Item)

}