package com.gym.ui.features.historial.formRegistrosPorFecha

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import kotlinx.coroutines.CoroutineScope

@Composable
fun FormRegistrosPorFecha(
    onIrAtras: () -> Unit,
    scope: CoroutineScope,
    snackbarHostState: SnackbarHostState
) {
    Column {
        Text("Form Registros Por Fecha")
    }
}