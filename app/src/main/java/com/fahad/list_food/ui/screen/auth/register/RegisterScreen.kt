
package com.fahad.auth_firebase.ui.screen.register

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.fahad.auth_fierbase_bottomnavigation.ui.screen.navigation.auth.AuthScreen
import com.fahad.auth_firebase.domain.model.Response
import com.fahad.auth_firebase.ui.screen.compenets.DisplayError
import com.fahad.auth_firebase.ui.screen.compenets.EmailAndPasswordInputs
import com.fahad.auth_firebase.ui.screen.compenets.NavigationText
import com.fahad.auth_firebase.util.Button.LoadingButton
import com.fahad.auth_firebase.util.image.AsyncImageProfile
import com.fahad.list_food.ui.screen.auth.register.RegisterViewModel

@Composable
fun RegisterScreen(
    registerViewModel: RegisterViewModel,
    navController: NavController
) {
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var name by rememberSaveable { mutableStateOf("") }
    var photoUri by rememberSaveable { mutableStateOf<Uri?>(null) }
    val registrationResult by registerViewModel.registrationState.collectAsState()

    println("println $registrationResult")

    // Get the result of the image picker
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        photoUri = uri
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),

            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Welcome to the Registration Page",
                color = MaterialTheme.colorScheme.primary,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = "Please fill in the details below to create an account",
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Center the image and show an icon if no image is selected
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                // Display the selected image or show an icon
                if (photoUri != null) {
                    AsyncImageProfile(photoUrl = photoUri.toString())
                } else {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "No photo selected",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .size(80.dp)
                            .align(Alignment.Center)
                            .border(2.dp, Color.Yellow, CircleShape)
                    )
                }
            }

            // Button to open the image picker
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 2.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = { launcher.launch("image/*") },
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text("Select Photo")
                }

                // Button to clear the selected image
                if (photoUri != null) {
                    Button(
                        onClick = { photoUri = null },
                        modifier = Modifier
                            .padding(8.dp)
                            .background(MaterialTheme.colorScheme.secondary, shape = CircleShape)
                    ) {
                        Text("Clear")
                    }
                }
            }

            EmailAndPasswordInputs(
                name = name,
                onNameChange = { name = it },
                email = email,
                onEmailChange = { email = it },
                password = password,
                onPasswordChange = { password = it },
                isError = registrationResult is Response.Failure,
                showNameField = true
            )

            // Spacer to create some space between input fields and button
            Spacer(modifier = Modifier.height(16.dp))

            // Display error message if registrationResult is a failure
            DisplayError(registrationResult)

            // Register Button
            LoadingButton(
                text = "Register",
                isLoading = registerViewModel.isLoading,
                enabled = !(email.isBlank() || password.isBlank() || name.isBlank() || photoUri == null),
                textloading = "Registering...",
                onClick = {
                    registerViewModel.registerUser(email, password, name, photoUri.toString(), navController)
                }
            )
            NavigationText(
                text = "Already have an account? Login",
                onClick = {
                    navController.navigate(AuthScreen.LOGIN.route)
                }
            )
        }
    }
}


















