package com.fahad.list_food.ui.screen.auth.profile

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

import com.fahad.auth_firebase.util.Button.LoadingButton
import com.fahad.auth_firebase.util.snackBar.SnackbarWrapperProfile

import com.fahad.auth_firebase.util.image.AsyncImageProfile
import com.fahad.list_food.ui.screen.UserDataViewModel

@Composable
fun ProfileScreen(
    navController: NavController, userDataViewModel: UserDataViewModel
) {
    val user by userDataViewModel.user.collectAsState()
    val displayName = user?.displayName
    val email = user?.email
    val photoUrl = user?.photoUrl

    val error = userDataViewModel.profileError.collectAsState().value
    val success = userDataViewModel.profileSuccess.collectAsState().value
    val isLoading = userDataViewModel.isLoading.collectAsState().value
    val isEmailVerified by userDataViewModel.isEmailVerified.collectAsState()

    val largeRadialGradient = MaterialTheme.colorScheme.background.copy(alpha = 0.1f)




    Box(
        modifier = Modifier
            .fillMaxSize()

            .background(largeRadialGradient)
            .padding(10.dp), contentAlignment = Alignment.TopCenter
    ) {

        Column(
            modifier = Modifier

                .fillMaxSize()
                .padding(top = 30.dp, bottom = 30.dp)

                .background(MaterialTheme.colorScheme.surface)
                .border(
                    2.dp, MaterialTheme.colorScheme.secondary, shape = RoundedCornerShape(25.dp)
                )
                .fillMaxWidth()

                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            if (!isEmailVerified) {

                VerifyEmailCard(
                    isLoading = isLoading,
                    onVerifyEmailClicked = userDataViewModel::markEmailAsVerified,

                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )


            }

                AsyncImageProfile(photoUrl = photoUrl)







            Spacer(modifier = Modifier.height(8.dp))

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(16.dp)
            ) {


                UserInfoRow(icon = Icons.Default.Person, text = displayName ?: "")

                Spacer(modifier = Modifier.height(8.dp))

                UserInfoRow(icon = Icons.Default.Email, text = email ?: "")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { navController.navigate("edit_profile") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
            ) {
                Text("Edit Profile", color = Color.White)
            }

            Button(
                onClick = {
                    userDataViewModel.logout()
                    navController.navigate("login")
                },
                modifier = Modifier
                    .padding(top = 8.dp)
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
            ) {
                Text("Sign Out", color = Color.White)
            }
        }
    }

    SnackbarWrapperProfile(
        success = success, error = error, onDismiss = userDataViewModel::clearMessages


    )
}


@Composable
fun UserInfoRow(icon: ImageVector, text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .background(
                MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f),
                shape = RoundedCornerShape(20.dp)
            )
            .padding(10.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 1f)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = text,
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp,
            fontFamily = FontFamily.Monospace,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}




@Composable
fun VerifyEmailCard(
    isLoading: Boolean,
    onVerifyEmailClicked: () -> Unit,
    modifier: Modifier = Modifier,

    ) {
    var isButtonClicked by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
    ) {
        Column(
            modifier = Modifier.padding(8.dp), horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                if (isButtonClicked) "if you have already verified your email, please sign out and sign in again or click on already verified button below"
                else "Verify your email to get full access.Check your email and click on the link to verify your email",


                modifier = Modifier.padding(8.dp),

                fontSize = 12.sp,
                color = Color.White,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
            )

            Spacer(modifier = Modifier.height(4.dp))


            LoadingButton(
                isLoading = isLoading,
                text = if (isButtonClicked) "already verified"
                else "Verify Email",
                onClick = {
                    isButtonClicked = true
                    onVerifyEmailClicked()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                enabled = !isLoading,
                textloading = "Verifying email..."
            )


        }


    }
}







