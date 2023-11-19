package com.fahad.list_food.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.fahad.list_food.data.local.BookItem
import com.fahad.list_food.data.local.entities.FavoriteItem
import com.fahad.list_food.model.FavoriteViewModel
import com.fahad.list_food.model.FoodViewModel


@Composable
fun ItemDetailsScreen(
    item: BookItem,
    viewModel: FoodViewModel,
    favoriteViewModel: FavoriteViewModel, // Inject the FavoriteViewModel
    navController: NavController
) {
    val isBookInFavorites by favoriteViewModel.isBookInFavorites(item.title).collectAsState(false)




    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .height(
                        250.dp
                    )
            ) {
                // Blurred or translucent background image
                Image(
                    painter = painterResource(id = item.imageResId),
                    contentDescription = null,
                    modifier = Modifier
                        .background(
                            Brush.horizontalGradient(
                                colors = listOf(
                                    Color.Transparent, Color.Black
                                ), startX = 0.0f, endX = 200.0f
                            )
                        )
                        .fillMaxSize()
                        .graphicsLayer(alpha = 0.5f),
                    contentScale = ContentScale.FillWidth
                )

                // Your main content (e.g., the centered image)
                Image(
                    painter = painterResource(id = item.imageResId),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(5.dp)
                        .size(280.dp)
                        .align(Alignment.Center),
                    contentScale = ContentScale.FillHeight
                )

                // Back button
                IconButton(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(16.dp)
                        .background(Color.White, RoundedCornerShape(16.dp))
                ) {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        modifier = Modifier.size(32.dp),
                        tint = Color.Black
                    )
                }


            }
        }

        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .clip(RoundedCornerShape(16.dp))
            )

            {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically

                ) {
                    Button(
                        onClick = { viewModel.addToCart(item) },
                        modifier = Modifier
                            .height(40.dp)
                            .size(40.dp)
                            .weight(1f),
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Icon(
                                Icons.Default.ShoppingCart,
                                contentDescription = "Add to Cart",
                                modifier = Modifier.size(40.dp)
                            )
                            Text(
                                "Add to Cart", modifier = Modifier.padding(start = 4.dp)
                            )
                        }


                    }
                    IconButton(
                        onClick = {
                            //add to favorite
                                  if (isBookInFavorites) {
                                        favoriteViewModel.deleteFromFavorites(FavoriteItem(
                                            title = item.title,
                                            description = item.description,
                                            imageResId = item.imageResId,
                                            price = item.price
                                        ))

                                  } else {
                                      favoriteViewModel.addToFavorite(item)
                                  }
                        }, modifier = Modifier

                            .padding(16.dp)
                            .background(Color.White, RoundedCornerShape(16.dp))
                    ) {
                        Icon(
                            Icons.Default.Favorite,
                            contentDescription = "Favorite",
                            modifier = Modifier.size(32.dp),
                            tint = if (isBookInFavorites) Color.Red else Color.Black
                        )
                    }
                }








                Text(
                    text = item.title,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    fontStyle = FontStyle.Italic,


                    modifier = Modifier.align(Alignment.CenterHorizontally)

                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .padding(horizontal = 5.dp),

                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,


                    ) {
                    Text(
                        text = "Author: ${item.author}",
                        fontSize = 16.sp,
                        fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
                        fontWeight = FontWeight.Bold


                    )
                    Text(
                        text = "Year: ${item.publicationYear}",
                        fontSize = 16.sp,
                        fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
                        fontWeight = FontWeight.Bold
                    )
                }



                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .background(
                            MaterialTheme.colorScheme.primary, RoundedCornerShape(25.dp)
                        )
                        .padding(10.dp),

                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,

                    ) {
                    Text(
                        text = "Price: $${item.price}",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,

                        color = if (isSystemInDarkTheme()) Color.Black else Color.White
                    )

                    Text(
                        text = "Pages: ${item.pages}",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,

                        color = if (isSystemInDarkTheme()) Color.Black else Color.White
                    )


                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Description:", fontSize = 20.sp, fontWeight = FontWeight.Bold
                )

                Text(
                    text = item.description, fontSize = 16.sp, textAlign = TextAlign.Justify
                )

            }
        }
    }
}


