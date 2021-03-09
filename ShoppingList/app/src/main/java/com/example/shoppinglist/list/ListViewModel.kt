import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.enigmacamp.myviewmodel.ResourceState
import com.example.shoppinglist.data.listeners.ItemClickListenerInterface
import com.example.shoppinglist.repository.ItemRepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(private val repository: ItemRepositoryInterface) : ViewModel(), ItemClickListenerInterface {

    private var _itemsLiveData = MutableLiveData<ResourceState>()
    private var _updateLiveData = MutableLiveData<ResourceState>()

    val itemsLiveData: LiveData<ResourceState>
        get() {
            return _itemsLiveData
        }
    val updateLiveData: LiveData<ResourceState>
        get() {
            return _updateLiveData
        }

    fun getAllData() {
        CoroutineScope(Dispatchers.IO).launch {
            _itemsLiveData.postValue(ResourceState.loading())
            val response = repository.getAllItem()
            if (response.isSuccessful) {
                response.body()?.let {
                    Log.d("DATA", "$it")
                    _itemsLiveData.postValue(ResourceState.success(it))
                }
            } else {
                _itemsLiveData.postValue(ResourceState.fail("error"))
            }
        }
    }

    override fun onDelete(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.deleteItem(id)
        }
    }

    override fun onEdit(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.getItemById(id)
            if(response.isSuccessful) {
                _itemsLiveData.postValue(ResourceState.success(response.body()))
            } else {
                _itemsLiveData.postValue(ResourceState.fail("error"))
            }
        }
    }
}