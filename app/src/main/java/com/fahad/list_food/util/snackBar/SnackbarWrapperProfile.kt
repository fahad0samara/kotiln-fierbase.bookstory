package com.fahad.auth_firebase.util.snackBar


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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

import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch



@Composable
fun SnackbarWrapperProfile(
    success: String?,
    error: String?,
    onDismiss: () -> Unit = {}
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    DisposableEffect(Unit) {
        // This block will be executed when the effect is disposed
        onDispose {
            snackbarHostState.currentSnackbarData?.dismiss()
            onDismiss()
        }
    }

    LaunchedEffect(error, success) {
        if (error != null || success != null) {
            val message = error ?: success ?: return@LaunchedEffect
            val actionLabel = if (error != null) "Dismiss" else "OK"

            coroutineScope.launch {
                snackbarHostState.showSnackbar(
                    message = message,
                    actionLabel = actionLabel,
                    duration = SnackbarDuration.Short
                )
            }
        }
    }

    SnackbarHost(
        hostState = snackbarHostState,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Snackbar(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = error ?: success!!)
        }
    }
}








