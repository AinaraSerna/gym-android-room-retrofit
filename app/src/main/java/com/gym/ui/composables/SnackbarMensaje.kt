package com.gym.ui.composables

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState

suspend fun SnackbarMensaje(mensaje: String, snackbarHostState: SnackbarHostState) {
    snackbarHostState.currentSnackbarData?.dismiss()
    snackbarHostState.showSnackbar(
        message = mensaje,
        duration = SnackbarDuration.Short
    )
}