package com.fahad.list_food.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "items")
data class Item(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val description: String,
    val imageResId: Int,
    val price: Double,
    var quantity: Int = 1
)





