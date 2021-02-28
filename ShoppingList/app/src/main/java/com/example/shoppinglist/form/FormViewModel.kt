package com.example.shoppinglist.form

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.enigmacamp.myviewmodel.ResourceState
import com.example.shoppinglist.data.model.Item
import com.example.shoppinglist.data.repository.ItemRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class FormViewModel(val repository: ItemRepository) : ViewModel() {
    private var _itemLiveData = MutableLiveData<Item>()
    private var _isItemValid = MutableLiveData<ResourceState>()

    val itemLiveData: LiveData<Item>
        get() {
            return _itemLiveData
        }

    val isItemValid: LiveData<ResourceState>
        get() {
            return _isItemValid
        }

    fun save(item: Item) {
        _itemLiveData.value = repository.save(item)
    }

    fun inputItemValidation(item: Item) {
        GlobalScope.launch {
            _isItemValid.postValue(ResourceState.loading())
            delay(3000)
            if (!item.date.isNullOrBlank() && !item.name.isNullOrBlank() && item.quantity > 0 && !item.note.isNullOrBlank()) {
                _isItemValid.postValue(ResourceState.success(true))
            } else {
                _isItemValid.postValue(ResourceState.fail("Input data cannot empty"))
            }
        }
    }
}