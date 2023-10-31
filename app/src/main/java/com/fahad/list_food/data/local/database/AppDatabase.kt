package com.fahad.list_food.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fahad.list_food.data.local.entities.Item
import com.fahad.list_food.data.local.dao.ItemDao

@Database(entities = [Item::class], version = 2, exportSchema = false)
 abstract  class AppDatabase : RoomDatabase() {
    abstract fun itemDao(): ItemDao
}

