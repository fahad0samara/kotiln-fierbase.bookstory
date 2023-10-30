package com.fahad.list_food.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fahad.list_food.data.local.entities.Item
import com.fahad.list_food.data.local.repository.ItemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FoodViewModel @Inject constructor(private val itemRepository: ItemRepository) : ViewModel() {
    val cart: Flow<List<Item>> = itemRepository.getAllItems()

    val availableItems = listOf("apple", "banana", "orange", "grapes")

    fun addToCart(itemName: String) {
        viewModelScope.launch {
            val item = Item(name = itemName, description = "Fruit")
            itemRepository.insertItem(item)
        }
    }
     fun deleteItem(item: Item) {
     viewModelScope.launch {
         itemRepository.deleteItem(item)
     }
 }
}





