package com.fahad.list_food.ui.navigation.bottom

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

import androidx.hilt.navigation.compose.hiltViewModel

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.fahad.list_food.model.FavoriteViewModel
import com.fahad.list_food.model.FoodViewModel

import com.fahad.list_food.ui.navigation.Graph
import com.fahad.list_food.ui.screen.Home
import com.fahad.list_food.ui.screen.ItemDetailsScreen


import com.fahad.list_food.ui.screen.UserDataViewModel
import com.fahad.list_food.ui.screen.auth.profile.EditProfileScreen
import com.fahad.list_food.ui.screen.auth.profile.ProfileScreen
import com.fahad.list_food.ui.screen.cart.CartScreen
import com.fahad.list_food.ui.screen.favorite.FavoriteItemsScreen

@Composable
fun BottomBarNavigation(navController: NavHostController,
) {
  val userDataViewModel: UserDataViewModel = hiltViewModel()
  val viewModel: FoodViewModel = hiltViewModel()
  val favoriteViewModel: FavoriteViewModel = hiltViewModel()
  LaunchedEffect(userDataViewModel.user) {
    userDataViewModel.getUserData() // Trigger fetching user data if not already done

  }




  NavHost(
    navController = navController,
    route = Graph.HOME,
    startDestination = BottomBar.Home.route
  ) {
    composable(route = BottomBar.Home.route) {
      Home(viewModel, navController,userDataViewModel)
    }

    composable(route = BottomBar.Cart.route) {
      CartScreen(viewModel)
    }

    composable(route = BottomBar.Settings.route) {
      FavoriteItemsScreen(favoriteViewModel, navController)

    }


    composable(route = BottomBar.Profile.route) {
      ProfileScreen(
        navController = navController,
        userDataViewModel = userDataViewModel
      )
    }
      composable(route =  "edit_profile") {
        EditProfileScreen(
          navController = navController, userDataViewModel = userDataViewModel
        )
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




//    searchNavGraph(navController = navController)
  }
}

fun NavGraphBuilder.searchNavGraph(navController: NavHostController) {

  navigation(
    route = Graph.Search,
    startDestination = SearchNavGraph.Search.route
  ) {
    composable(route = SearchNavGraph.Search.route) {

    }

    }
  }


sealed class SearchNavGraph(val route: String) {
  data object Search : SearchNavGraph(route = "Search")

}


