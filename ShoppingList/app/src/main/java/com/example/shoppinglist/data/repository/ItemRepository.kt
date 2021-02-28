package com.example.shoppinglist.data.repository

import com.example.shoppinglist.data.model.Item
import java.util.*

class ItemRepository : ItemRepositoryInterface{
    companion object {
        var itemList = arrayListOf(
            Item(
                UUID.randomUUID().toString(),
                "1/1/2020",
                "123",
                123,
                "note"
            ),
            Item(
                UUID.randomUUID().toString(),
                "2/1/2020",
                "123",
                123,
                "note"
            ),
            Item(
                UUID.randomUUID().toString(),
                "1/3/2020",
                "123",
                123,
                "note"
            )
        )
    }

    override fun list(): List<Item> = itemList
    override fun save(item: Item): Item {
        if (item.id == "") {
            item.id = UUID.randomUUID().toString()
            itemList.add(item)
        }
        return item
    }

    override fun delete(item: Item): Item {
        val i : Int = itemList.indexOf(item)
        itemList.removeAt(i)
        return item
    }

}