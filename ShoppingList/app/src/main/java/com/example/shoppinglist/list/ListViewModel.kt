import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoppinglist.data.listeners.ItemClickListenerInterface
import com.example.shoppinglist.data.model.Item
import com.example.shoppinglist.data.repository.ItemRepositoryInterface

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

    init {
        loadItemData(0)
    }

    fun loadItemData(page: Int) {
        _itemsLiveData.value = repository.list(page)
    }

    private fun loadItemData() {
        _itemsLiveData.value = repository.list()
    }

    private fun getItemData(item: Item) {
        _itemLiveData.value = repository.findByItem(item)
    }

    override fun onDelete(item: Item) {
        repository.delete(item)
        loadItemData()
    }

    override fun onUpdate(item: Item) {
        getItemData(item)
    }
}