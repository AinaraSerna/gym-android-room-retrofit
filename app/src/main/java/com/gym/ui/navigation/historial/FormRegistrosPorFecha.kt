package com.gym.ui.navigation.historial

import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gym.ui.features.registros.formNuevosRegistros.FormNuevosRegistrosScreen
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
        FormNuevosRegistrosScreen(
            onIrAtras = onIrAtras
        )
    }
}