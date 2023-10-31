package com.fahad.list_food

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.fahad.list_food.navigation.AppNavigation
import com.fahad.list_food.model.FoodViewModel
import com.fahad.list_food.ui.theme.ListfoodTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {



        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                Color.TRANSPARENT, Color.TRANSPARENT
            ),
            navigationBarStyle = SystemBarStyle.light(
                Color.TRANSPARENT, Color.TRANSPARENT
            )

        )

        setContent {
            ListfoodTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,



                ) {
                    AppNavigation()






                }
            }
        }
    }
}




//
//    @Composable
//    fun ItemList( viewModel: FoodViewModel, navController: NavController) {
//        val items = listOf("عنصر 1", "عنصر 2", "عنصر 3", "عنصر 4")
//        val cart = remember { mutableStateListOf<String>() }
//        val cartView = viewModel.getCart()
//        val itemsView = viewModel.getItems()
//
//        Column {
//            LazyColumn {
//                    items(itemsView) { item ->
//                        Row(
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .padding(8.dp)
//                        ) {
//                            Text(text = item, fontSize = 20.sp)
//                            Spacer(modifier = Modifier.weight(1f))
//                            IconButton(
//                                onClick = { viewModel.addToCart(item) }
//                            ) {
//                                Icon(imageVector = Icons.Default.Add, contentDescription = null)
//                            }
//                        }
//                    }
//                }
//
//
//                LazyColumn {
//                    items(cartView) { item ->
//                        Text(text = item, fontSize = 20.sp, modifier = Modifier.padding(8.dp))
//                        //delete item from cart
//                        IconButton(
//                            onClick = { viewModel.deleteItem(item) }
//                        ) {
//                            Icon(imageVector = Icons.Default.Delete, contentDescription = null)
//                        }
//                    }
//
//                }
//
//            }
//        Row {
//            Text("your cart")
//            Spacer(modifier = Modifier.weight(1f))
//            Button(
//                onClick = { navController.navigate("cart") },
//                modifier = Modifier.padding(8.dp)
//            ) {
//                Text(text = "عرض السلة")
//            }
//        }
//
//
//    }
//@Composable
//suspend fun CartScreen(viewModel: FoodViewModel){
//    val cart = viewModel.getCart()
//
//    Column(modifier=Modifier.fillMaxSize()){
//        Text("your cart")
//        LazyColumn {
//            items(cart) { item ->
//                Text(text = item, fontSize = 20.sp, modifier = Modifier.padding(8.dp))
//                //delete item from cart
//                IconButton(
//                    onClick = { viewModel.deleteItem(item) }
//                ) {
//                    Icon(imageVector = Icons.Default.Delete, contentDescription = null)
//                }
//            }
//
//        }
//    }
//
//}


