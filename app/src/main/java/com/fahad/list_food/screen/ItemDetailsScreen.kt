package com.fahad.list_food.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.fahad.list_food.data.local.FoodItem
import com.fahad.list_food.model.FoodViewModel

@Composable
fun ItemDetailsScreen(
    item: FoodItem,
    viewModel: FoodViewModel,
    navController: NavHostController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Display item name, description, and image
        Text(text = item.name, fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Text(text = item.description, fontSize = 16.sp, color = Color.Gray)

        Image(
            painter = painterResource(id = item.imageResId),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(top = 8.dp)
        )

        // Display item price
        Text(text = "Price: $${item.price}", fontSize = 18.sp)

        // Add to Cart button
        Button(
            onClick = { viewModel.addToCart(item) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Text(text = "Add to Cart")
        }

        // Back button to return to the home screen
        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        ) {
            Text(text = "Back to Home")
        }
    }
}
