package com.fahad.list_food.model

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fahad.list_food.R
import com.fahad.list_food.data.local.FoodItem
import com.fahad.list_food.data.local.FoodType
import com.fahad.list_food.data.local.entities.Item
import com.fahad.list_food.data.local.repository.ItemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FoodViewModel @Inject constructor(private val itemRepository: ItemRepository) : ViewModel() {
    val cart: Flow<List<Item>> = itemRepository.getAllItems()

    val availableItems = listOf(
        FoodItem("Apple", "Fresh and delicious", R.drawable.apple, 1.0, FoodType.MainCourse),
        FoodItem("Banana", "Ripe and tasty", R.drawable.banana, 0.75, FoodType.MainCourse),
        FoodItem("Orange", "Sweet and juicy", R.drawable.orange, 1.25, FoodType.MainCourse),
        FoodItem("Grapes", "Red and seedless", R.drawable.grapes, 2.0, FoodType.SideDish)
    )

    val groupedItems = availableItems.groupBy { it.foodType }



    fun addToCart(item: FoodItem) {
        viewModelScope.launch {
             val newItem = Item(
                 name = item.name,
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





