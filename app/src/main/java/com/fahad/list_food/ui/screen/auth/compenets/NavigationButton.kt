package com.fahad.list_food.ui.screen.auth.compenets

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.fahad.list_food.ui.theme.dimens

@Composable
fun NavigationText(
    text: String,
    onClick: () -> Unit
) {
    TextButton(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = MaterialTheme.dimens.small3)
    ) {
        Text(text,
            style = MaterialTheme.typography.labelMedium,



          )
    }
}
