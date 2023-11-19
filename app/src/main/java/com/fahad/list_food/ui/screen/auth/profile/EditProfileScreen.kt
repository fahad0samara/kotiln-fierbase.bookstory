package com.fahad.list_food.ui.screen.auth.profile

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField

import androidx.compose.material3.Text

import androidx.compose.runtime.Composable

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf


import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


import com.fahad.auth_firebase.util.Button.LoadingButton
import com.fahad.auth_firebase.util.snackBar.SnackbarWrapperEdit
import com.fahad.auth_firebase.util.image.AsyncImageProfile
import com.fahad.list_food.ui.screen.UserDataViewModel


@Composable
fun EditProfileScreen(
    navController: NavController, userDataViewModel: UserDataViewModel
) {
    var displayName by rememberSaveable {
        mutableStateOf(userDataViewModel.user.value?.displayName ?: "")
    }
    var photoUri by rememberSaveable {
        mutableStateOf(userDataViewModel.user.value?.photoUrl?.let { Uri.parse(it) })
    }

    val error = userDataViewModel.editProfileError.collectAsState().value
    val success = userDataViewModel.editProfileSuccess.collectAsState().value
    val isLoading = userDataViewModel.isLoading.collectAsState().value


    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        photoUri = uri
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)

            .border(2.dp, Color.Gray, shape = RoundedCornerShape(16.dp))
            .padding(16.dp),
        contentAlignment = Alignment.TopStart
    ) {
        // Cancel Button
        IconButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .padding(8.dp)
                .background(
                    color = MaterialTheme.colorScheme.secondary, shape = CircleShape
                )
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = Color.White
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 56.dp), // Adjusted top padding to make space for the back button
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Display the selected image or show an icon with background
                AsyncImageProfile(photoUrl= photoUri.toString()
                )



            // Button to open the image picker
            Button(
                onClick = { launcher.launch("image/*") },
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondary, contentColor = Color.White
                )
            ) {
                Text("Change Image")
            }

            // Display Name Input
            OutlinedTextField(value = displayName,
                onValueChange = { displayName = it },
                label = { Text("Display Name") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
            )

            // LoadingButton
            LoadingButton(
                text = "Save Changes",
                isLoading = isLoading,
                enabled = !(photoUri == null || displayName.isBlank()),
                textloading = "Saving...",
                onClick = {
                    photoUri?.let { uri ->
                        userDataViewModel.updateUserProfile(
                            displayName, uri, navController
                        )
                    }
                },
            )
        }
    }

    SnackbarWrapperEdit(
        errorMessage = error,
        successMessage = success,
        onDismiss = {
            userDataViewModel.clearMessages()


        }
    )



}






