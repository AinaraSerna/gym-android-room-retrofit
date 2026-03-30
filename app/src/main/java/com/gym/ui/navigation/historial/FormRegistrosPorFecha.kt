package com.gym.ui.navigation.historial

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gym.ui.features.registros.formNuevosRegistros.FormNuevosRegistrosScreen
import kotlinx.serialization.Serializable

@Serializable
data object FormRegistrosPorFechaRoute

fun NavGraphBuilder.formRegistrosPorFechaDestination(
    onIrAtras : () -> Unit
) {
    composable<FormRegistrosPorFechaRoute> {
        FormNuevosRegistrosScreen(
            onIrAtras = onIrAtras
        )
    }
}