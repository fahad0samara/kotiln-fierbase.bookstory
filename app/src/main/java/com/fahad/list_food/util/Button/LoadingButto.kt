package com.fahad.auth_firebase.util.Button

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun LoadingButton(
    text: String,
    isLoading: Boolean,
    onClick: () -> Unit,
    enabled: Boolean = true, // Default to true
    modifier: Modifier = Modifier,
    textloading: String
) {
    Button(
        onClick = onClick,
        enabled = enabled, // Set the enabled state based on the provided boolean
        modifier = modifier.fillMaxWidth().padding(0.dp, 0.dp, 0.dp, 16.dp)
    ) {
        if (isLoading) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                CircularProgressIndicator(
                    modifier = Modifier.size(16.dp).align(Alignment.CenterVertically),
                    color = Color.Black
                )
                Spacer(modifier = Modifier.width(8.dp))
                //the texy be as the loading
                Text(text = textloading)


            }
        } else {
            Text(text = text)
        }
    }
}
