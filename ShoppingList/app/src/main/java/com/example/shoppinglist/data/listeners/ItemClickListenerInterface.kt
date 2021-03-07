package com.example.shoppinglist.data.listeners

import com.example.shoppinglist.data.model.Item

interface ItemClickListenerInterface {
    fun onDelete(id: Int)
    fun onEdit(id: Int)
}