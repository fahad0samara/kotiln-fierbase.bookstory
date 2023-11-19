package com.fahad.auth_firebase.util.snackBar


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration

import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember

import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch


@Composable
fun SnackbarWrapperEdit(
    errorMessage: String? = null, successMessage: String? = null, onDismiss: () -> Unit = {}
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    DisposableEffect(Unit) {
        onDispose {
            snackbarHostState.currentSnackbarData?.dismiss()
            onDismiss()
        }
    }

    fun showSnackbar(message: String, actionLabel: String) {
        coroutineScope.launch {
            snackbarHostState.showSnackbar(
                message = message, actionLabel = actionLabel, duration = SnackbarDuration.Short
            )
        }
    }

    LaunchedEffect(errorMessage, successMessage) {
        errorMessage?.let {
            showSnackbar(it, "Dismiss")
        }

        successMessage?.let {
            showSnackbar(it, "OK")
        }
    }

    SnackbarHost(
        hostState = snackbarHostState, modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)

    ) {
        Snackbar(containerColor = if (errorMessage != null) Color.Red else Color.Green,
            modifier = Modifier.padding(16.dp),
            action = {
                if (successMessage != null) {
                    Text(
                        text = "OK",
                        modifier = Modifier.padding(8.dp),
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.secondary
                    )
                } else {
                    Text(
                        text = "Dismiss",
                        modifier = Modifier.padding(8.dp),
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }


            }) {
            Text(text = errorMessage ?: successMessage!!)
        }
    }
}







