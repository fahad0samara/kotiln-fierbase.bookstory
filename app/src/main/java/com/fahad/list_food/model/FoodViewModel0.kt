package com.fahad.list_food.model

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.fahad.list_food.R

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

//@HiltViewModel
//class FoodViewModel0 @Inject constructor() : ViewModel() {
//    // List of items in the cart
//    private val cartItems = mutableStateListOf<FoodItem>()
//
//    // List of available items
//    val availableItems = listOf(
//        FoodItem("Apple", "Fresh and delicious", R.drawable.apple, 1.0),
//        FoodItem("Banana", "Ripe and tasty", R.drawable.banana, 0.75),
//        FoodItem("Orange", "Sweet and juicy", R.drawable.orange, 1.25),
//        FoodItem("Grapes", "Red and seedless", R.drawable.grapes, 2.0)
//    )
//
//
//
//    // Add an item to the cart
//    fun addToCart(item: FoodItem) {
//        cartItems.add(item)
//    }
//
//    // Remove an item from the cart
//    fun removeFromCart(item: FoodItem) {
//        cartItems.remove(item)
//    }
//
//    // Calculate the total price of items in the cart
//    fun calculateTotal(): Double {
//        var total = 0.0
//        for (item in cartItems) {
//            total += item.price
//        }
//        return total
//    }
//}
