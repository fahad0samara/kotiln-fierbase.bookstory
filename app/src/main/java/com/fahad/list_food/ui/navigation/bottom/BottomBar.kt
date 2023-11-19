package com.fahad.list_food.ui.navigation.bottom

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBar(
  val route: String,
  val title: String,
  val icon: ImageVector
) {
  data object Home : BottomBar(
    route = "HOME",
    title = "HOME",
    icon = Icons.Default.Home
  )



  data object Cart : BottomBar(
    route = "CART",
    title = "CART",
    icon = Icons.Default.ShoppingCart
  )
  data object Settings : BottomBar(
    route = "SETTINGS",
    title = "SETTINGS",
    icon = Icons.Default.Settings
  )

  data object Profile : BottomBar(
    route = "PROFILE",
    title = "PROFILE",
    icon = Icons.Default.Person
  )
}