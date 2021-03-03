package com.example.shoppinglist.list

import ListViewModel
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoppinglist.R
import com.example.shoppinglist.data.repository.ItemRepository
import com.example.shoppinglist.databinding.FragmentListBinding


class ListFragment : Fragment() {

    lateinit var binding: FragmentListBinding
    lateinit var viewModel: ListViewModel
    lateinit var rvAdapter: ListViewAdapter
    private var page = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
        subscribe()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(layoutInflater)
        binding.apply {

            nextBtn.setOnClickListener{
                viewModel.loadItemData(++page)
                pageTv.text = page.toString()
            }

            prevBtn.setOnClickListener {
                if (page > 0) {
                    viewModel.loadItemData(--page)
                    pageTv.text = page.toString()
                }
            }
            rvAdapter = ListViewAdapter(viewModel)

            recyclerViewItem.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = rvAdapter
            }
        }
        return binding.root
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory{
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val repo = ItemRepository()
                return ListViewModel(repo) as T
            }
        }).get(ListViewModel::class.java)
    }

    private fun subscribe() {
        viewModel.itemsLiveData.observe(this){
            rvAdapter.setData(it)
        }
        viewModel.itemLiveData.observe(this) {
            Navigation.findNavController(requireView())
                .navigate(
                    R.id.action_listFragment_to_formFragment,
                    bundleOf("item_value" to it)
                )
        }
    }
}