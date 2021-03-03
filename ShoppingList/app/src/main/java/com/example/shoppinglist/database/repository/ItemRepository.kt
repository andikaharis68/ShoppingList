package com.example.shoppinglist.database.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.shoppinglist.data.model.Item
import com.example.shoppinglist.database.ItemDao

class ItemRepository(private val itemDao: ItemDao) : ItemRepositoryInterface {

    override val itemsList: MutableLiveData<List<Item>> = itemDao.findAllItemDao()

    override suspend fun addItemRepository(item : Item) {
        itemDao.addItemDao(item)
    }

    override fun findItemByIdRepository(id:Int): MutableLiveData<Item> {
        return itemDao.findItemByIdDao(id)
    }

    override suspend fun updateItemRepository(item : Item){
        itemDao.updateItemDao(item)
    }

    override suspend fun deleteItemRepository(user: Item){
        itemDao.deleteItemDao(user)
    }
}