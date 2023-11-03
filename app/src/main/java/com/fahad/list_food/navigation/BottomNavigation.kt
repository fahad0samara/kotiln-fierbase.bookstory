package com.fahad.list_food.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.fahad.list_food.model.FavoriteViewModel
import com.fahad.list_food.model.FoodViewModel
import com.fahad.list_food.screen.CartScreen
import com.fahad.list_food.screen.FavoriteItemsScreen
import com.fahad.list_food.screen.ItemDetailsScreen
import com.fahad.list_food.screen.ItemList
import com.fahad.list_food.screen.SearchScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val viewModel: FoodViewModel = hiltViewModel()
    val favoriteViewModel: FavoriteViewModel = hiltViewModel()

    Scaffold(
        bottomBar = {
            NavigationBar(
                contentColor = Color.White,
                containerColor = MaterialTheme.colorScheme.background
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route


                val items = listOf("foodItems", "cart", "Favorite")

                items.forEach { item ->
                    NavigationBarItem(
                        icon = {
                            when (item) {
                                "foodItems" -> Icon(Icons.Default.Home, contentDescription = "Home")
                                "cart" -> Icon(
                                    Icons.Default.ShoppingCart,
                                    contentDescription = "Cart"
                                )

                                "Favorite" -> Icon(
                                    Icons.Default.Favorite,
                                    contentDescription = "Favorite"
                                )
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
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("foodItems") {

                ItemList(viewModel, navController)
            }
            composable("cart") {
                CartScreen(viewModel)
            }
            composable("Favorite") {
                FavoriteItemsScreen(favoriteViewModel, navController)
            }


            composable("SearchScreen") {
                SearchScreen(
                    viewModel,
                    navController
                )
            }



            composable(
                "itemDetails/{itemName}",
                arguments = listOf(navArgument("itemName") { type = NavType.StringType })
            ) { backStackEntry ->
                val itemName = backStackEntry.arguments?.getString("itemName")
                val selectedItem =
                    viewModel.groupedItems.values.flatten().find { it.author == itemName }
                selectedItem?.let { item ->
                    ItemDetailsScreen(item, viewModel, favoriteViewModel, navController) // Pass the FavoriteViewModel
                } ?: run {
                    Text(text = "Item not found")
                }
            }
        }
    }
}












