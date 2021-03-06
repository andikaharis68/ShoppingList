package com.example.shoppinglist.repository

import androidx.lifecycle.MutableLiveData
import com.example.shoppinglist.data.model.Item
import retrofit2.Response

interface ItemRepositoryInterface {
    suspend fun getItem(): Response<Item>
}