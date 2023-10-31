package com.fahad.list_food.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fahad.list_food.model.FoodViewModel

@Composable
fun CartScreen(viewModel: FoodViewModel){
    val cartItems by viewModel.cart.collectAsState(emptyList())


    Column(modifier= Modifier.fillMaxSize()
        .padding(8.dp),


    ){
        Text("your cart")
        LazyColumn {
            items(cartItems) { item ->
                Text(text = item.name, fontSize = 20.sp)
                Text(text = "$${item.price}", fontSize = 20.sp)
                Image(
                    painter = painterResource(id = item.imageResId),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 8.dp)
                )




                IconButton(
                    onClick = { viewModel.deleteItem(item) }
                ) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = null)
                }
            }

        }
    }


}