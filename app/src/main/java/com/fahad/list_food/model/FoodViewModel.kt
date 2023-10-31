package com.fahad.list_food.model

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fahad.list_food.R


import com.fahad.list_food.data.local.availableBooks

import com.fahad.list_food.data.local.entities.Item
import com.fahad.list_food.data.local.repository.ItemRepository

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class FoodViewModel @Inject constructor(private val itemRepository: ItemRepository) : ViewModel() {
    val cart: Flow<List<Item>> = itemRepository.getAllItems()



    val groupedItems = availableBooks.groupBy { it.bookType }



    fun addToCart(item: com.fahad.list_food.data.local.BookItem) {
        viewModelScope.launch {
             val newItem = Item(
                 name = item.author,
                 description = item.description,
                    imageResId = item.imageResId,
                    price = item.price

             )
             itemRepository.insertItem(newItem)
         }
    }





     fun deleteItem(item: Item) {
     viewModelScope.launch {
         itemRepository.deleteItem(item)
     }
 }
}





