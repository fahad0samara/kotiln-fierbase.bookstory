package com.fahad.list_food.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.fahad.list_food.model.FoodViewModel
import com.fahad.list_food.screen.CartScreen
import com.fahad.list_food.screen.ItemList

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val viewModel: FoodViewModel = hiltViewModel()

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                val items = listOf("foodItems", "cart")

                items.forEach { item ->
                    NavigationBarItem(
                        icon = { Icon(Icons.Default.Home, contentDescription = null) },
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
        }
    }



}

