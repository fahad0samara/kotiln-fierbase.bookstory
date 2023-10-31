package com.fahad.list_food.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.fahad.list_food.model.FoodViewModel

@Composable
fun ItemList(viewModel: FoodViewModel, navController: NavController) {
    val items = listOf("عنصر 1", "عنصر 2", "عنصر 3", "عنصر 4")
    val cart = remember { mutableStateListOf<String>() }
    val availableItems = viewModel.availableItems


    Column {
        LazyColumn {
            items(availableItems) { item ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Text(text = item.name, fontSize = 20.sp)
                    Spacer(modifier = Modifier.weight(1f))
                    Text(text = "$${item.price}", fontSize = 20.sp)
                    Image(
                        painter = painterResource(id = item.imageResId),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .padding(top = 8.dp)
                    )
                    IconButton(
                        onClick = { viewModel.addToCart(item) }
                    ) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = null)
                    }
                }
            }
        }

//
//        LazyColumn {
//            items(cartView) { item ->
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

    }}
