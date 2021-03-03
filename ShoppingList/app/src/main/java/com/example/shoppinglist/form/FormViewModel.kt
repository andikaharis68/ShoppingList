package com.example.shoppinglist.form

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.enigmacamp.myviewmodel.ResourceState
import com.example.shoppinglist.data.model.Item
import com.example.shoppinglist.database.repository.ItemRepository
import kotlinx.coroutines.*

class FormViewModel(private val repository: ItemRepository) : ViewModel() {

    private var _isItemValid = MutableLiveData<ResourceState>()
    private var _itemAdded = MutableLiveData<Item>()

    val isItemValid: LiveData<ResourceState>
        get() {
            return _isItemValid
        }

    val isItemAdded: LiveData<Item>
        get() {
            return _itemAdded
        }

    fun addItem(item:Item) {
        CoroutineScope(Dispatchers.IO).launch{
            _itemAdded = repository.addItemRepository(item) as MutableLiveData<Item>
        }
    }

    fun updateItem(item:Item){
        CoroutineScope(Dispatchers.IO).launch {
            _itemAdded = repository.updateItemRepository(item) as MutableLiveData<Item>
        }
    }

    fun inputItemValidation(item: Item) {
        CoroutineScope(Dispatchers.IO).launch{
            _isItemValid.postValue(ResourceState.loading())
            if (!item.date.isNullOrBlank() && !item.name.isNullOrBlank() && item.quantity > 0 && !item.note.isNullOrBlank()) {
                _isItemValid.postValue(ResourceState.success(true))
            } else {
                _isItemValid.postValue(ResourceState.fail("Input data cannot empty"))
            }
        }
    }
}




//    private var _itemLiveData = MutableLiveData<Item>()
//    private var _isItemValid = MutableLiveData<ResourceState>()
//
//    val itemLiveData: LiveData<Item>
//        get() {
//            return _itemLiveData
//        }
//
//    val isItemValid: LiveData<ResourceState>
//        get() {
//            return _isItemValid
//        }
//
//    fun save(item: Item) {
//        _itemLiveData.value = repository.save(item)
//    }
