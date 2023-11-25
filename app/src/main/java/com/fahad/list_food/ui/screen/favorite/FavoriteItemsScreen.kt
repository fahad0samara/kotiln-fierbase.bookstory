package com.fahad.list_food.ui.screen.favorite

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.fahad.list_food.data.local.entities.FavoriteItem
import com.fahad.list_food.ui.theme.dimens

@Composable
fun FavoriteItemsScreen(
  viewModel: FavoriteViewModel, navController: NavController
) {
    val favoriteItems by viewModel.favorite.collectAsState(emptyList())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Favorite Items", fontSize = 24.sp, fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (favoriteItems.isEmpty()) {
          Box(
            modifier = Modifier
              .fillMaxSize()
              .padding(MaterialTheme.dimens.medium1),
            contentAlignment = Alignment.Center
          ) {
            Column(
              modifier = Modifier
                .fillMaxWidth()
                .padding(MaterialTheme.dimens.medium1),
              horizontalAlignment = Alignment.CenterHorizontally
            ) {
              Text(
                text = "No Favorite Items Found",

                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                fontWeight = FontWeight.Bold
              )
              Text(
                text = "Add Items to your favorite list",

                fontSize = MaterialTheme.typography.labelMedium.fontSize,
                fontWeight = FontWeight.Bold
              )

            }

          }
        } else {
            LazyColumn {
                items(favoriteItems) { item ->
                    FavoriteItemCard(item = item,
                        navController = navController,
                        onDeleteClick = { viewModel.deleteFromFavorites(item) })
                }
            }
        }
    }
}

@Composable
fun FavoriteItemCard(
    item: FavoriteItem, onDeleteClick: () -> Unit,navController: NavController
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp),
                elevation = CardDefaults.cardElevation(
               defaultElevation = 8.dp,
                pressedElevation = 8.dp,
                disabledElevation = 0.dp
            )



    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = item.imageResId),
                    contentDescription = null,
                    modifier = Modifier
                        .size(100.dp)
                        .clip(shape = RoundedCornerShape(4.dp)),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.width(16.dp))

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = item.title, fontSize = 18.sp, fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "${item.price}$",
                         fontSize = 18.sp, fontWeight = FontWeight.Bold
                    )
                }

                IconButton(
                    onClick = onDeleteClick,
                    modifier = Modifier

                        .clip(shape = CircleShape).background(Color.Red) .size(40.dp)
                ) {
                    Icon(Icons.Default.Delete, contentDescription = "Delete",
                        tint = Color.White)

                }
            }
        }
    }
}


