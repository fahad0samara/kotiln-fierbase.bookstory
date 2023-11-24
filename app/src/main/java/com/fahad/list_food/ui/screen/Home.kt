package com.fahad.list_food.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import coil.transform.CircleCropTransformation
import com.fahad.list_food.R
import com.fahad.list_food.data.local.BookItem
import com.fahad.list_food.data.local.BookType
import com.fahad.list_food.model.FoodViewModel
import com.fahad.list_food.ui.navigation.bottom.SearchNavGraph
import com.fahad.list_food.ui.theme.dimens
import java.time.LocalTime

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
fun Home(
  viewModel: FoodViewModel,
  navController: NavController,
    userDataViewModel: UserDataViewModel
) {
  val selectedCategory = remember { mutableStateOf(BookType.Fiction) }
  val itemsForCategory = viewModel.groupedItems[selectedCategory.value] ?: emptyList()
  val user by userDataViewModel.user.collectAsState() // Observe the user state
  val painter = rememberAsyncImagePainter(
    ImageRequest.Builder(LocalContext.current).data(data =
    user?.photoUrl
    ).apply(block = fun ImageRequest.Builder.() {
      crossfade(true)
      transformations(CircleCropTransformation())
      scale(Scale.FILL)
    }).build()

    )

    val currentUser = user ?: return



  Column(
    modifier = Modifier
      .fillMaxSize()
      .background(MaterialTheme.colorScheme.background)
      .padding(top = 15.dp)

      // Add padding for status bar
      .systemBarsPadding()
      .padding(bottom = 66.dp)



  ){
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(
          horizontal = 16.dp
        ),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.SpaceBetween
    ) {
      val greeting = when (LocalTime.now().hour) {
        in 0..11 -> "Good morning"
        in 12..17 -> "Good afternoon"
        else -> "Good evening"
      }

      Column {
        Text(
          text = "$greeting, ${currentUser.displayName}",
          fontSize = MaterialTheme.typography.headlineMedium.fontSize
        )
        Text(
          text = "Let's find your favorite book",
          fontSize = MaterialTheme.typography.labelMedium.fontSize,
          fontStyle = FontStyle.Italic,
            color = MaterialTheme.colorScheme.primary
        )
      }

      Spacer(modifier = Modifier.width(16.dp))

      // Display user's profile picture or a placeholder image if not available
      Image(
           painter = painter,
        contentDescription = null,
        modifier = Modifier
          .size(50.dp)
          .background(Color(0xFF91F1FF), CircleShape),
        contentScale = ContentScale.Crop
      )
    }

    Spacer(modifier = Modifier.height(16.dp))



    // Search bar with TextField

    OutlinedTextField(
      value = "",
      onValueChange = { },
      modifier = Modifier
        .fillMaxWidth()
        .padding(MaterialTheme.dimens.medium1)
        .height(MaterialTheme.dimens.logoSize1)
        .onFocusChanged {
          if (it.isFocused) {
            navController.navigate(
              SearchNavGraph.Search.route

            )
          }
        },


      trailingIcon = {
        Icon(
          Icons.Default.Search,
          modifier = Modifier
            .padding(MaterialTheme.dimens.small2)
            .size(MaterialTheme.dimens.medium2),

          contentDescription = null,
          tint = Color(0xFF91F1FF)
        )
      },
      colors = TextFieldDefaults.colors(

        unfocusedIndicatorColor = Color.Transparent,
        focusedIndicatorColor = Color(0xFF91F1FF),
        disabledContainerColor = Color.Transparent,
        disabledIndicatorColor = Color.Transparent,
        disabledTextColor = Color.Transparent,
        disabledPlaceholderColor = Color.Transparent,
        disabledLeadingIconColor = Color.Transparent,
        disabledTrailingIconColor = Color.Transparent,


        ),
      shape = CutCornerShape(MaterialTheme.dimens.small3),


      placeholder = { Text("Search for books") }
    )

    CategorySelection(selectedCategory)

    FoodList(itemsForCategory, navController)
  }
}


@Composable
fun CategorySelection(selectedCategory: MutableState<BookType>) {
  LazyRow(
    modifier = Modifier
      .fillMaxWidth()
      .padding(top = 8.dp)
      .height(MaterialTheme.dimens.logoSize1),
    horizontalArrangement = Arrangement.spacedBy(2.dp),

    ) {
    items(BookType.entries) { category ->
      Button(
        onClick = { selectedCategory.value = category },
        modifier = Modifier
          .padding(start = 5.dp, end = 10.dp),
        colors = ButtonDefaults.buttonColors(

          containerColor = if (selectedCategory.value == category) Color(0xFF006973) else Color(
            0xFF91F1FF
          ),
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
    items: List<BookItem>,
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
                navController.navigate("itemDetails/${item.author}")
            })
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodItem(
    food: BookItem,
    onTap: (BookItem) -> Unit
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
                text = food.author,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                  .padding(horizontal = 4.dp)
                  .fillMaxWidth(),
                color = if (isSystemInDarkTheme()) {
                    Color(0xFF91F1FF)

                } else {
                    Color(0xFF006973)

                },
                fontSize = if (food.author > 20.toString()) 14.sp else 17.sp,
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
                Row(
                    modifier = Modifier,


                    verticalAlignment = Alignment.CenterVertically,


                    ) {
                    Text(
                        text = food.pages.toString(),
                        fontSize = 18.sp,
                        color = if (isSystemInDarkTheme()) {
                            colorResource(id = R.color.white)
                        } else {
                            colorResource(id = R.color.black)
                        }
                    )
                    Icon(
                        imageVector = Icons.Default.List,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(end = 4.dp),
                        tint = if (isSystemInDarkTheme()) {
                            Color(0xFF91F1FF)

                        } else {
                            Color(0xFF006973)

                        },
                    )


                }

            }
        }
    }
}






