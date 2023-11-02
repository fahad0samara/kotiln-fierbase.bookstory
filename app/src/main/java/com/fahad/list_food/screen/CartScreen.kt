package com.fahad.list_food.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.fahad.list_food.data.local.BookItem
import com.fahad.list_food.data.local.entities.Item
import com.fahad.list_food.model.FoodViewModel

@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun CartScreen(
    viewModel: FoodViewModel
) {
    val cartItems by viewModel.cart.collectAsState(emptyList())

    var totalPrice by remember { mutableStateOf(0.0) }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier= Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween


        )

        {
            Text(
                text = "Cart",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Button(
                onClick = {
                    viewModel.clearCart()
                    totalPrice = 0.0
                },
                modifier = Modifier
                    .padding(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
            ) {
                Text(text = "Clear Cart", color = Color.White)
            }

        }



        if (cartItems.isNotEmpty()) {
            LazyColumn {
                items(cartItems) { item ->
                    CartItemRow(item, viewModel) { priceChange ->
                        totalPrice += priceChange
                    }
                }
            }



            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Total Price: $${totalPrice}",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Button(
                        onClick = {
                            // Implement checkout logic here
                        }
                    ) {
                        Text(text = "Checkout")
                    }

                    Button(
                        onClick = {
                            // Implement cancel logic here
                        }
                    ) {
                        Text(text = "Cancel")
                    }
                }
            }
        } else {
            Text(
                text = "Your cart is empty.",
                fontSize = 16.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        }
    }
}

@Composable
fun CartItemRow(
    item: Item,
    viewModel: FoodViewModel,
    onPriceChange: (Double) -> Unit
) {
    var quantity by remember { mutableStateOf(item.quantity) }
    var priceChange by remember { mutableStateOf(0.0) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(1.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(id = item.imageResId),
                contentDescription = null,
                modifier = Modifier.size(100.dp).padding(8.dp),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(
                    text = item.title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Price: $${item.price}",
                    fontSize = 16.sp,
                    color = if (isSystemInDarkTheme()) Color.Gray else Color.Black
                )
            }

            IconButton(
                onClick = {
                    viewModel.deleteItem(item)
                    priceChange = -item.price * quantity
                    onPriceChange(priceChange)
                },
                modifier = Modifier.padding(end = 8.dp, top = 8.dp),
                content = {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete",
                        tint = Color.Red
                    )
                }
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            ActionIconButton(
                onClick = {
                    if (quantity > 1) {
                        viewModel.decrementItem(item)
                        quantity--
                        priceChange -= item.price
                        onPriceChange(priceChange)
                    }
                },
                content = {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "Decrement",
                        tint = Color.White
                    )
                },
                backgroundColor = Color.Red // Background color for the decrement button
            )

            Text(text = quantity.toString())

            ActionIconButton(
                onClick = {
                    viewModel.incrementItem(item)
                    quantity++
                    priceChange += item.price
                    onPriceChange(priceChange)
                },
                content = {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Increment",
                        tint = Color.White
                    )
                },
                backgroundColor = Color.Green // Background color for the increment button
            )
        }

    }
}

@Composable
fun ActionIconButton(
    onClick: () -> Unit,
    content: @Composable () -> Unit,
    backgroundColor: Color
) {
    Box(
        modifier = Modifier
            .size(36.dp)
            .background(backgroundColor,CircleShape)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}
















