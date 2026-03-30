package com.gym.ui.navigation.ejercicios

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gym.ui.features.ejercicios.detallesEjercicio.DetallesEjercicioScreen
import kotlinx.serialization.Serializable

@Serializable
data object DetallesEjercicioRoute

fun NavGraphBuilder.detallesEjercicioDestination(
    onIrAtras : () -> Unit
) {
    composable<DetallesEjercicioRoute> {
        DetallesEjercicioScreen(
            onIrAtras = onIrAtras
        )
    }
}