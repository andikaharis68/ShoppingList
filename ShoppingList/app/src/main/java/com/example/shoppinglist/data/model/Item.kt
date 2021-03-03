package com.example.shoppinglist.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "item")
data class Item (

    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val date: String,
    val name: String,
    val quantity: Int,
    val note: String
)