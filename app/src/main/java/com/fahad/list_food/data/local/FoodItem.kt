package com.fahad.list_food.data.local

enum class FoodType {
    MainCourse, SideDish,Snack, Beverage, Dessert
}
data class FoodItem(
    val name: String,
    val description: String,
    val imageResId: Int,
    val price: Double,
    val foodType: FoodType
)