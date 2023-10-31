package com.fahad.list_food.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.fahad.list_food.R
import com.fahad.list_food.data.local.FoodItem
import com.fahad.list_food.data.local.FoodType
import com.fahad.list_food.model.FoodViewModel

//@Composable
//fun ItemList(viewModel: FoodViewModel, navController: NavController) {
//    val items = listOf("عنصر 1", "عنصر 2", "عنصر 3", "عنصر 4")
//    val cart = remember { mutableStateListOf<String>() }
//    val availableItems = viewModel.availableItems
//
//
//    Column {
//        LazyColumn {
//            items(availableItems) { item ->
//                Column(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(8.dp)
//                ) {
//                    Text(text = item.name, fontSize = 20.sp)
//                    Spacer(modifier = Modifier.weight(1f))
//                    Text(text = "$${item.price}", fontSize = 20.sp)
//                    Image(
//                        painter = painterResource(id = item.imageResId),
//                        contentDescription = null,
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .height(200.dp)
//                            .padding(top = 8.dp)
//                    )
//                    IconButton(
//                        onClick = { viewModel.addToCart(item) }
//                    ) {
//                        Icon(imageVector = Icons.Default.Add, contentDescription = null)
//                    }
//
//                    Button(
//                        onClick = {
//                            // Navigate to a screen to show more details
//                            navController.navigate("itemDetails/${item.name}")
//                        },
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(top = 8.dp)
//                    ) {
//                        Text(text = "See More Details")
//                    }
//                }
//            }
//        }
//
////
////        LazyColumn {
////            items(cartView) { item ->
////                Text(text = item, fontSize = 20.sp, modifier = Modifier.padding(8.dp))
////                //delete item from cart
////                IconButton(
////                    onClick = { viewModel.deleteItem(item) }
////                ) {
////                    Icon(imageVector = Icons.Default.Delete, contentDescription = null)
////                }
////            }
////
////        }
//
//    }}

@Composable
fun ItemList(
    viewModel: FoodViewModel,
    navController: NavController
) {
    val selectedCategory = remember { mutableStateOf(FoodType.MainCourse) }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        CategorySelection(selectedCategory)

        val itemsForCategory = viewModel.groupedItems[selectedCategory.value] ?: emptyList()
        FoodList(itemsForCategory, navController)
    }
}

@Composable
fun CategorySelection(selectedCategory: MutableState<FoodType>) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
            .height(50.dp),
        horizontalArrangement = Arrangement.spacedBy(2.dp),

    ) {
        items(FoodType.entries) { category ->
            Button(
                onClick = { selectedCategory.value = category },
                modifier = Modifier
                    .padding(start = 5.dp, end = 10.dp),
                colors = ButtonDefaults.buttonColors(

                    containerColor = if (selectedCategory.value == category)  Color(0xFF006973) else Color(0xFF91F1FF),
                ),
            ) {
                Text(
                    text = category.name,
                    color = if (selectedCategory.value == category) Color.White else Color.Black
                )
            }
        }
    }
}

@Composable
fun FoodList(
    items: List<FoodItem>,
    navController: NavController
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(items) { item ->
            FoodItem(item, onTap = {
                navController.navigate("itemDetails/${item.name}")
            })
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodItem(
    food: FoodItem,
    onTap: (FoodItem) -> Unit
) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp),
        onClick = { onTap(food) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = if (isSystemInDarkTheme()) {
                        colorResource(id = R.color.black)
                    } else {
                        colorResource(id = R.color.white)
                    }
                )
        ) {
            Image(
                painter = painterResource(id = food.imageResId),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 170.dp, max = 170.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = food.name,
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .fillMaxWidth(),
                color = if (isSystemInDarkTheme()) {
                    colorResource(id = R.color.white)
                } else {
                    colorResource(id = R.color.black)
                },
                fontSize = if (food.name.length > 20) 14.sp else 17.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "${food.price}$",
                    modifier = Modifier,
                    fontSize = 18.sp,
                    color = if (isSystemInDarkTheme()) {
                        colorResource(id = R.color.white)
                    } else {
                        colorResource(id = R.color.black)
                    }
                )
                Text(
                    text = "${food.price} min",
                    fontSize = 18.sp,
                    color = if (isSystemInDarkTheme()) {
                        colorResource(id = R.color.white)
                    } else {
                        colorResource(id = R.color.black)
                    }
                )
            }
        }
    }
}






