package com.fahad.auth_firebase.ui.screen.compenets

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.fahad.auth_firebase.domain.model.Response

@Composable
 fun DisplayError(loginResult: Response<Any>) {
    if (loginResult is Response.Failure) {
        Text(
            text = (loginResult as Response.Failure).exception.message ?: "Unknown error",
            color = Color.Red,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        )
    }
}
