package com.fahad.list_food.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.fahad.list_food.model.FavoriteViewModel
import com.fahad.list_food.model.FoodViewModel
import com.fahad.list_food.ui.screen.CartScreen
import com.fahad.list_food.ui.screen.FavoriteItemsScreen
import com.fahad.list_food.ui.screen.ItemDetailsScreen
import com.fahad.list_food.ui.screen.ItemList
import com.fahad.list_food.ui.screen.SearchScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val viewModel: FoodViewModel = hiltViewModel()
    val favoriteViewModel: FavoriteViewModel = hiltViewModel()

    val favoriteItems by favoriteViewModel.favorite.collectAsState(emptyList())
    val favoriteItemCount = favoriteItems.size

    val cartItems by viewModel.cart.collectAsState(emptyList())
    val cartItemCount = cartItems.size

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
                            val icon = when (item) {
                                "foodItems" -> Icons.Default.Home
                                "cart" -> Icons.Default.ShoppingCart
                                "Favorite" -> Icons.Default.Favorite
                                else -> null
                            }
                            icon?.let {
                                Row {
                                    Icon(it, contentDescription = item)
                                    val itemCount = when (item) {
                                        "cart" -> cartItemCount
                                        "Favorite" -> favoriteItemCount
                                        else -> 0
                                    }
                                    if (itemCount > 0) {
                                        Box(
                                            modifier = Modifier
                                                .padding(2.dp)
                                                .background(Color.Red, CircleShape),
                                        ) {
                                            Text(
                                                text = itemCount.toString(),
                                                color = Color.White,
                                                fontSize = 12.sp,
                                                fontWeight = FontWeight.Bold,
                                                modifier = Modifier.padding(2.dp)
                                            )
                                        }
                                    }
                                }
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
                SearchScreen(viewModel, navController)
            }

            composable(
                "itemDetails/{itemName}",
                arguments = listOf(navArgument("itemName") { type = NavType.StringType })
            ) { backStackEntry ->
                val itemName = backStackEntry.arguments?.getString("itemName")
                val selectedItem = viewModel.groupedItems.values.flatten()
                    .find { it.author == itemName }
                selectedItem?.let { item ->
                    ItemDetailsScreen(item, viewModel, favoriteViewModel, navController)
                } ?: run {
                    Text(text = "Item not found")
                }
            }
        }
    }
}









