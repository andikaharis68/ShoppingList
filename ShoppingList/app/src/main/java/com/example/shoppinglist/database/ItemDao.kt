package com.example.shoppinglist.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.example.shoppinglist.data.model.Item

@Dao
interface ItemDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addItemDao(item: Item)

    @Query("SELECT * FROM item ORDER BY id ASC")
    fun findAllItemDao(): MutableLiveData<List<Item>>

    @Query("SELECT * FROM item WHERE id = :id")
    fun findItemByIdDao(id: Int) : MutableLiveData<Item>

    @Delete
    suspend fun deleteItemDao(item: Item)

    @Update
    suspend fun updateItemDao(item : Item)
}