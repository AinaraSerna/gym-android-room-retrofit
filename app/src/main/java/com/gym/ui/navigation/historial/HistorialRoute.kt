package com.gym.ui.navigation.historial

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gym.ui.features.historial.HistorialScreen
import kotlinx.serialization.Serializable

@Serializable
data object HistorialRoute

fun NavGraphBuilder.historialDestination(
    onIrAFormRegistrosPorFecha: (Int) -> Unit
) {
    composable<HistorialRoute> {
        HistorialScreen(
            onIrAFormRegistrosPorFecha = onIrAFormRegistrosPorFecha
        )
    }
}