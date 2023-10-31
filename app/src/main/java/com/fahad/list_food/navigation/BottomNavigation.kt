package com.fahad.list_food.navigation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItemDefaults.contentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.fahad.list_food.model.FoodViewModel
import com.fahad.list_food.screen.CartScreen
import com.fahad.list_food.screen.ItemDetailsScreen
import com.fahad.list_food.screen.ItemList

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val viewModel: FoodViewModel = hiltViewModel()

    Scaffold(
        bottomBar = {
            NavigationBar(
                contentColor = Color.White,
                containerColor = MaterialTheme.colorScheme.background) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route


                val items = listOf("foodItems", "cart")

                items.forEach { item ->
                    NavigationBarItem(
                        icon = {
                            when (item) {
                                "foodItems" -> Icon(Icons.Default.Home, contentDescription = "Home")
                                "cart" -> Icon(Icons.Default.ShoppingCart, contentDescription = "Cart")
                            }
                        },
                        label = { Text(text = item) },



                        selected = currentRoute == item,
                        onClick = {
                            navController.navigate(item) {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "foodItems",
            modifier = androidx.compose.ui.Modifier.padding(innerPadding)
        ) {
            composable("foodItems") {

                ItemList(viewModel, navController)
            }
            composable("cart") {
                CartScreen(viewModel)
            }
            composable(
                "itemDetails/{itemName}",
                arguments = listOf(navArgument("itemName") { type = NavType.StringType })
            ) { backStackEntry ->
                val itemName = backStackEntry.arguments?.getString("itemName")
                val selectedItem = viewModel.groupedItems.values.flatten().find { it.author == itemName }
                selectedItem?.let { item ->
                    ItemDetailsScreen(item, viewModel, navController)
                } ?: run {
                    Text(text = "Item not found")
                }
            }
        }
    }
}












