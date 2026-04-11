package com.gym.ui.navigation.historial

import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gym.ui.features.historial.formRegistrosPorFecha.FormRegistrosPorFecha
import kotlinx.coroutines.CoroutineScope
import kotlinx.serialization.Serializable

@Serializable
data object FormRegistrosPorFechaRoute

fun NavGraphBuilder.formRegistrosPorFechaDestination(
    onIrAtras: () -> Unit,
    scope: CoroutineScope,
    snackbarHostState: SnackbarHostState
) {
    composable<FormRegistrosPorFechaRoute> {
        FormRegistrosPorFecha (
            onIrAtras = onIrAtras,
            scope = scope,
            snackbarHostState = snackbarHostState
        )
    }
}