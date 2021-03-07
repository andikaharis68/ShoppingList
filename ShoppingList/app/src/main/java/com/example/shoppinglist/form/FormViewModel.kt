package com.example.shoppinglist.form

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.enigmacamp.myviewmodel.ResourceState
import com.example.shoppinglist.data.model.Item
import com.example.shoppinglist.data.model.ItemRequest
import com.example.shoppinglist.repository.ItemRepository
import kotlinx.coroutines.*

class FormViewModel(private val repository: ItemRepository) : ViewModel() {

    private var _itemLiveData = MutableLiveData<ResourceState>()
    private var _isValid = MutableLiveData<ResourceState>()

    val itemLiveData : LiveData<ResourceState>
        get() {
            return _itemLiveData
        }

    val isValid: LiveData<ResourceState>
        get() {
            return _isValid
        }

    fun addData(item: ItemRequest) {
        CoroutineScope(Dispatchers.IO).launch {
            _itemLiveData.postValue(ResourceState.loading())
            val response =
                    if(item.id == 0) {
                        repository.addItem(item)
                    } else {
                        repository.editItem(item)
                    }

            if (response.isSuccessful) {
                response.body()?.let {
                    Log.d("DATA", "$it")
                    _itemLiveData.postValue(ResourceState.success(it))
                }
            } else {
                _itemLiveData.postValue(ResourceState.fail("error"))
            }
        }
    }

    fun inputItemValidation(item: ItemRequest) {
        CoroutineScope(Dispatchers.IO).launch{
            _isValid.postValue(ResourceState.loading())
            if (!item.date.isNullOrBlank() && !item.name.isNullOrBlank() && item.quantity > 0 && !item.note.isNullOrBlank()) {
                _isValid.postValue(ResourceState.success(true))
            } else {
                _isValid.postValue(ResourceState.fail("Input data cannot empty"))
            }
        }
    }
}

