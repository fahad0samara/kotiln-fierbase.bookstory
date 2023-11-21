package com.fahad.list_food.ui.screen.auth.compenets

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import com.fahad.auth_firebase.domain.model.Response
import com.fahad.list_food.ui.theme.dimens

@Composable
 fun DisplayError(loginResult: Response<Any>) {
    if (loginResult is Response.Failure) {
        Text(
            text = (loginResult ).exception.message ?: "Unknown error",
            color = Color.Red,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = MaterialTheme.dimens.small1)
        )
    }
}
