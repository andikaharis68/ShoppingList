package com.example.shoppinglist.form

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoppinglist.data.model.Item
import com.example.shoppinglist.data.repository.ItemRepository

class FormViewModel(val repository: ItemRepository) : ViewModel() {
    private var _itemLiveData = MutableLiveData<Item>()

    val itemLiveData: LiveData<Item>
        get() {
            return _itemLiveData
        }

    fun save(item: Item) {
        _itemLiveData.value = repository.save(item)
    }

}