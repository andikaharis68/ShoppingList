package com.example.shoppinglist.list

import ListViewModel
import android.os.Bundle
import android.util.Log
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
import com.enigmacamp.myviewmodel.ResourceStatus
import com.example.shoppinglist.R
import com.example.shoppinglist.data.model.Item
import com.example.shoppinglist.repository.ItemRepository
import com.example.shoppinglist.databinding.FragmentListBinding
import com.example.shoppinglist.form.FormViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : Fragment() {

    lateinit var binding: FragmentListBinding
    lateinit var viewModel: ListViewModel
    lateinit var rvAdapter: ListViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
        subscribe()
        viewModel.getAllData()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(layoutInflater)
        binding.apply {
            rvAdapter = ListViewAdapter(viewModel)
            recyclerViewItem.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = rvAdapter
            }
        }
        return binding.root
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
    }

    private fun subscribe() {
        viewModel.itemsLiveData.observe(this) {
            when (it.status) {
                ResourceStatus.LOADING -> Log.d("APP", "Loading..")
                ResourceStatus.SUCCESS -> {
                    val data: List<Item> = it.data as List<Item>
                    rvAdapter.setData(data)
                }
            }
        }

        viewModel.updateLiveData.observe(this) {
            val response = it.data as Item
            Navigation.findNavController(requireView())
                    .navigate(
                            R.id.action_listFragment_to_formFragment,
                            bundleOf("item_value" to response)
                    )
        }
    }
}