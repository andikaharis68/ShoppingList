import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoppinglist.data.listeners.ItemClickListenerInterface
import com.example.shoppinglist.data.model.Item
import com.example.shoppinglist.database.repository.ItemRepositoryInterface
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListViewModel(private val repository: ItemRepositoryInterface) : ViewModel(), ItemClickListenerInterface {

    private var _itemsLiveData = MutableLiveData<List<Item>>()
    private var _itemLiveData = MutableLiveData<Item>()

    val itemsLiveData: LiveData<List<Item>>
        get() {
            loadItemData()
            return _itemsLiveData
        }

    val itemLiveData: LiveData<Item>
        get() {
            return _itemLiveData
        }

    private fun loadItemData() {
        _itemsLiveData = repository.itemsList
    }

    override fun onDelete(item: Item) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.deleteItemRepository(item)
            loadItemData()
        }
    }

    override fun onUpdate(id:Int) {
        CoroutineScope(Dispatchers.IO).launch {
            _itemLiveData = repository.findItemByIdRepository(id)
        }
    }
}