package com.fahad.list_food.data.local.repository

import com.fahad.list_food.data.local.dao.ItemDao
import com.fahad.list_food.data.local.entities.FavoriteItem
import com.fahad.list_food.data.local.entities.Item
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ItemRepository @Inject constructor(private val itemDao: ItemDao) {
    suspend fun insertItem(item: Item) = itemDao.insert(item)

    suspend fun deleteItem(item: Item) = itemDao.delete(item)

    suspend fun deleteAllItems() = itemDao.deleteAllItems()

    fun getAllItems(): Flow<List<Item>> {
        return itemDao.getAllItems()
    }

    suspend fun getItemById(itemId: Long) = itemDao.getItemById(itemId)

    suspend fun getAllItemNames(): List<String> {
        return itemDao.getAllItemNames()
    }

    suspend fun incrementItemQuantity(itemId: Long) {
        itemDao.incrementItemQuantity(itemId)
    }

    suspend fun decrementItemQuantity(itemId: Long) {
        itemDao.decrementItemQuantity(itemId)
    }


}
