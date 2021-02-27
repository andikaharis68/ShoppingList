package com.example.shoppinglist.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoppinglist.data.model.Item
import com.example.shoppinglist.data.repository.ItemRepository

class ListViewModel(private val repository: ItemRepository): ViewModel() {
    private var _itemsLiveData = MutableLiveData<List<Item>>()

    val itemLiveData: LiveData<List<Item>>
        get() {
            loadItemData()
            return _itemsLiveData
        }

    fun loadItemData() {
        _itemsLiveData.value = repository.list()
    }


}