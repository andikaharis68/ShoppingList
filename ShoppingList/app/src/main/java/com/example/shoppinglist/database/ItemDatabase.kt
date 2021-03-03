package com.example.shoppinglist.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.shoppinglist.data.model.Item

@Database(entities = [Item::class], version = 1, exportSchema = false)
abstract class ItemDatabase: RoomDatabase() {

    abstract fun dao(): ItemDao

    companion object {
        @Volatile
        private var INTANCE: ItemDatabase? = null
        fun getDatabase(context: Context): ItemDatabase {
            //            cara kotlin
            return INTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        ItemDatabase::class.java,
                        "shopping_list_database"
                ).build()
                INTANCE = instance
                instance
            }

//            cara panjang
//            val tempInstance = INTANCE
//            if (tempInstance != null) {
//                return tempInstance
//            }
//
//            synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    ItemDatabase::class.java,
//                    "user_database"
//                ).build()
//                INTANCE = instance
//                return instance
//            }
        }
    }
}