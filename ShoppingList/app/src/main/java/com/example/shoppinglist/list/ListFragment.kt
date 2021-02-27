package com.example.shoppinglist.list

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.example.shoppinglist.data.repository.ItemRepository
import com.example.shoppinglist.databinding.FragmentListBinding


class ListFragment : Fragment() {

    lateinit var binding: FragmentListBinding
    lateinit var viewModel: ListViewModel

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
        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ListFragment().apply {

            }
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
        viewModel.itemLiveData.observe(this){
            Log.d("SUBSCRIBE", "$it")
        }
    }
}