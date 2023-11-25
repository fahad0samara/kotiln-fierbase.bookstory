package com.fahad.list_food.ui.screen.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fahad.list_food.data.local.BookItem


import com.fahad.list_food.data.local.availableBooks


import com.fahad.list_food.data.local.entities.Item
import com.fahad.list_food.data.local.repository.ItemRepository

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CartViewModel @Inject constructor(private val itemRepository: ItemRepository):ViewModel() {
    val cart: Flow<List<Item>> = itemRepository.getAllItems()


    val groupedItems = availableBooks.groupBy { it.bookType }

    fun addToCart(item: BookItem) {
        viewModelScope.launch {
            val newItem = Item(
                title = item.title,
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

    fun clearCart() {
        viewModelScope.launch {
            itemRepository.deleteAllItems()
        }
    }

    fun incrementItem(item: Item) {
        viewModelScope.launch {
            itemRepository.incrementItemQuantity(item.id)
        }
    }

    fun decrementItem(item: Item) {
          viewModelScope.launch {
                itemRepository.decrementItemQuantity(item.id)
            }
        }









}









