package com.fahad.list_food.ui

//@Composable
//fun AppNavigation(
//    navController: NavHostController,
//    viewModel: FoodViewModel,
//    favoriteViewModel: FavoriteViewModel
//) {
//    val favoriteItems by favoriteViewModel.favorite.collectAsState(emptyList())
//    val favoriteItemCount = favoriteItems.size
//
//    val cartItems by viewModel.cart.collectAsState(emptyList())
//    val cartItemCount = cartItems.size
//
//    Scaffold(
//        bottomBar = {
//            NavigationBar(
//                contentColor = Color.White,
//                containerColor = MaterialTheme.colorScheme.background
//            ) {
//                val navBackStackEntry by navController.currentBackStackEntryAsState()
//                val currentRoute = navBackStackEntry?.destination?.route
//
//                val items = listOf("foodItems", "cart", "Favorite")
//
//                items.forEach { item ->
//                    NavigationBarItem(
//                        icon = {
//                            val icon = when (item) {
//                                "foodItems" -> Icons.Default.Home
//                                "cart" -> Icons.Default.ShoppingCart
//                                "Favorite" -> Icons.Default.Favorite
//                                else -> null
//                            }
//                            icon?.let {
//                                Row {
//                                    Icon(it, contentDescription = item)
//                                    val itemCount = when (item) {
//                                        "cart" -> cartItemCount
//                                        "Favorite" -> favoriteItemCount
//                                        else -> 0
//                                    }
//                                    if (itemCount > 0) {
//                                        Box(
//                                            modifier = Modifier
//                                                .padding(2.dp)
//                                                .background(Color.Red, CircleShape),
//                                        ) {
//                                            Text(
//                                                text = itemCount.toString(),
//                                                color = Color.White,
//                                                fontSize = 12.sp,
//                                                fontWeight = FontWeight.Bold,
//                                                modifier = Modifier.padding(2.dp)
//                                            )
//                                        }
//                                    }
//                                }
//                            }
//                        },
//                        label = { Text(text = item) },
//                        selected = currentRoute == item,
//                        onClick = {
//                            navController.navigate(item) {
//                                popUpTo(navController.graph.startDestinationId) {
//                                    saveState = true
//                                }
//                                launchSingleTop = true
//                                restoreState = true
//                            }
//                        }
//                    )
//                }
//            }
//        }
//    ) { innerPadding ->
//        // Make sure to create the NavHost only once
//        NavHost(
//            navController = navController,
//            startDestination = "foodItems",
//            modifier = Modifier.padding(innerPadding)
//        ) {
//            composable("foodItems") {
//                ItemList(viewModel, navController)
//            }
//            composable("cart") {
//                CartScreen(viewModel)
//            }
//            composable("Favorite") {
//                FavoriteItemsScreen(favoriteViewModel, navController)
//            }
//
//            composable("SearchScreen") {
//                SearchScreen(viewModel, navController)
//            }
//
//            composable(
//                "itemDetails/{itemName}",
//                arguments = listOf(navArgument("itemName") { type = NavType.StringType })
//            ) { backStackEntry ->
//                val itemName = backStackEntry.arguments?.getString("itemName")
//                val selectedItem = viewModel.groupedItems.values.flatten()
//                    .find { it.author == itemName }
//                selectedItem?.let { item ->
//                    ItemDetailsScreen(item, viewModel, favoriteViewModel, navController)
//                } ?: run {
//                    Text(text = "Item not found")
//                }
//            }
//        }
//    }
//}
//




