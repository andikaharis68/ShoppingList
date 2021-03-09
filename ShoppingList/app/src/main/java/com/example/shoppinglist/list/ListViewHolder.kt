package com.example.shoppinglist.list

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.data.listeners.ItemClickListenerInterface
import com.example.shoppinglist.data.model.Item
import com.example.shoppinglist.databinding.CardViewItemBinding


class ListViewHolder(view: View, val itemClickListenerInterface: ItemClickListenerInterface) :
    RecyclerView.ViewHolder(view) {
    private val binding = CardViewItemBinding.bind(view)

    fun bind(item: Item) {
        binding.apply {
            nameTv.text = item.name
            quantityTv.text = item.quantity.toString()
            dateTv.text = item.date
            noteTv.text = item.note
            deleteBtn.setOnClickListener{
                itemClickListenerInterface.onDelete(item.id)
            }
            cardItemUpdate.setOnClickListener{
                itemClickListenerInterface.onEdit(item.id)
            }
        }
    }
}