package com.example.shoppinglist.data.model

import android.os.Parcel
import android.os.Parcelable
import android.text.Editable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "item")
data class Item(
    @PrimaryKey(autoGenerate = true) val id : Int = 0,
    @ColumnInfo(name = "shopping_date") val date:String,
    @ColumnInfo(name = "item_name") val name : String,
    @ColumnInfo(name = "quantity") val quantity:Int,
    @ColumnInfo(name = "notes") val note:String) : Parcelable
{
    constructor(parcel: Parcel) : this(
        parcel.readInt()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readString()!!
    ) {
    }

    override fun describeContents() = 0

    override fun writeToParcel(p0: Parcel?, p1: Int) {
        p0?.writeString(id.toString())
        p0?.writeString(date)
        p0?.writeString(name)
        p0?.writeString(quantity.toString())
        p0?.writeString(note)
    }

    companion object CREATOR : Parcelable.Creator<Item> {

//        fun quantityCheck(editable: Editable): Int{
//            return if (ValidationApp.validationEditable(editable)){
//                editable.toString().toInt()
//            } else {
//                0
//            }
//        }

        override fun createFromParcel(parcel: Parcel): Item {
            return Item(parcel)
        }

        override fun newArray(size: Int): Array<Item?> {
            return arrayOfNulls(size)
        }
    }
}