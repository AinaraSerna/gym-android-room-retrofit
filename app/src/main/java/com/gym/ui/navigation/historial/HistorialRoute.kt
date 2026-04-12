package com.gym.ui.navigation.historial

import androidx.compose.runtime.collectAsState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gym.ui.features.historial.HistorialScreen
import com.gym.ui.features.historial.HistorialViewModel
import kotlinx.serialization.Serializable

@Serializable
data object HistorialRoute

fun NavGraphBuilder.historialDestination(
    historialVM: HistorialViewModel
) {
    composable<HistorialRoute> {
        HistorialScreen(
            historial = historialVM.historial.collectAsState().value,
            registroDeHistorialSeleccionado = historialVM.registroHistorialSeleccionado.collectAsState().value,
            onHistorialEvent = historialVM::onHistorialEvent
        )
    }
}