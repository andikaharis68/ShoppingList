package com.example.shoppinglist.form

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.enigmacamp.myviewmodel.ResourceStatus
import com.example.shoppinglist.R
import com.example.shoppinglist.data.model.Item
import com.example.shoppinglist.data.model.ItemRequest
import com.example.shoppinglist.repository.ItemRepository
import com.example.shoppinglist.databinding.FragmentFormBinding
import com.example.shoppinglist.util.component.LoadingDialog
import java.util.*

class FormFragment : Fragment() {
    private lateinit var binding : FragmentFormBinding
    private lateinit var viewModel: FormViewModel
    lateinit var loadingDialog: AlertDialog
    private var itemUpdate: Item? = null
    private var itemRequestValue: ItemRequest? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            itemUpdate = it.getParcelable<Item>("edit_item")
        }
        initModel()
        subscribe()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        loadingDialog = LoadingDialog.build(requireContext())
        binding = FragmentFormBinding.inflate(layoutInflater)
        binding.apply {
            itemUpdate?.apply {
                dateEt.setText(date)
                nameEt.editText?.setText(name)
                quantityEt.editText?.setText(quantity.toString())
                noteEt.editText?.setText(note)
            }
            dateEt.inputType = InputType.TYPE_NULL
            dateEt.setOnClickListener(View.OnClickListener {
                val datePickerDialog = activity?.let { it1 ->
                    DatePickerDialog(
                        it1, DatePickerDialog.OnDateSetListener
                        { view, year, monthOfYear, dayOfMonth ->
                            dateEt.setText(
                                "$year/$monthOfYear/$dayOfMonth",
                                TextView.BufferType.EDITABLE
                            );
                        }, year, month, day
                    )
                }
                datePickerDialog?.show()
            })
            submitBtn.setOnClickListener {
            var quantity: Int = if (quantityEt.editText?.text.toString().isNullOrEmpty()) {
                0
            } else {
                quantityEt.editText?.text.toString().toInt()
            }
                if (itemUpdate == null){
                    itemRequestValue = ItemRequest(
                        name = nameEt.editText?.text.toString(),
                        date = dateEt.text.toString(),
                        quantity = quantity,
                        note = noteEt.editText?.text.toString()
                    )
                    viewModel.inputItemValidation(itemUpdate!!)
                } else {
                    submitBtn.text = "UPDATE"
                    itemUpdate?.id?.let{it ->
                        itemRequestValue = ItemRequest(
                            id = it,
                            name = nameEt.editText?.text.toString(),
                            date = dateEt.text.toString(),
                            quantity = quantity,
                            note = noteEt.editText?.text.toString()
                        )
                        viewModel.inputItemValidation(itemRequestValue!!)
                    }
                }
            }
            cancelBtn.setOnClickListener{
                Navigation.findNavController(requireView()).popBackStack()
            }
            return binding.root
        }
    }

    private fun initModel() {
        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val repo =
                        ItemRepository()
                return FormViewModel(repo) as T
            }
        }).get(FormViewModel::class.java)
    }

    private fun subscribe() {
        viewModel.itemLiveData.observe(this){
            findNavController().navigate(R.id.action_formFragment_to_listFragment)
        }
        viewModel.isValid.observe(this) {
            when (it.status) {
                ResourceStatus.LOADING -> loadingDialog.show()
                ResourceStatus.SUCCESS -> {
                    loadingDialog.hide()
                    viewModel.addData(itemRequestValue!!)
                    println("succes")}
                ResourceStatus.FAIL -> {
                    loadingDialog.hide()
                    Toast.makeText(
                        requireContext(),
                        it.message,
                        Toast.LENGTH_LONG
                    ).show()
                    println("fail")}
            }
        }
    }
}