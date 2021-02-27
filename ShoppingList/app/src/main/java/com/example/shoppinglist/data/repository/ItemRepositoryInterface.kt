package com.example.shoppinglist.data.repository

import com.example.shoppinglist.data.model.Item

interface ItemRepositoryInterface {
    fun list(): List<Item>
    fun save(item : Item): Item
}