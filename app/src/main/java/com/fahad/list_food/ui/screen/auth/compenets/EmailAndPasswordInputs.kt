package com.fahad.auth_firebase.ui.screen.compenets

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.fahad.list_food.ui.theme.dimens

@Composable
fun EmailAndPasswordInputs(
    showNameField: Boolean,
    name:String,
    onNameChange:(String) -> Unit,
    email: String,
    onEmailChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    isError: Boolean
) {
    if (showNameField) {

        OutlinedTextField(
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text
            ),
            isError = isError,
            value = name,
            onValueChange = onNameChange,
            label = { Text("Name") },


            shape = MaterialTheme.shapes.large,
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, MaterialTheme.dimens.medium1, 0.dp, 0.dp)
        )
    }


    OutlinedTextField(
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email
        ),
        isError = isError,
        value = email,
        onValueChange = onEmailChange,
        label = { Text("Email") },
        shape = MaterialTheme.shapes.large,
        modifier = Modifier.fillMaxWidth().
        padding(0.dp, MaterialTheme.dimens.medium1, 0.dp, 0.dp)

    )

    OutlinedTextField(
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password
        ),
        isError = isError,
        value = password,
        onValueChange = onPasswordChange,
        label = { Text("Password") },
        visualTransformation = PasswordVisualTransformation(),
        shape = MaterialTheme.shapes.large,
        modifier = Modifier
            .fillMaxWidth()
          .padding(0.dp, MaterialTheme.dimens.medium1, 0.dp, 0.dp)
    )




}