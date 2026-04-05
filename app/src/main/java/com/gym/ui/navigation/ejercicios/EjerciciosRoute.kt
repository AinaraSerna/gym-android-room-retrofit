package com.gym.ui.navigation.ejercicios

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gym.ui.features.ejercicios.EjerciciosScreen
import kotlinx.serialization.Serializable

@Serializable
data object EjerciciosRoute

fun NavGraphBuilder.ejerciciosDestination(
    onIrADetallesEjercicio: () -> Unit
) {
    composable<EjerciciosRoute> {
        EjerciciosScreen(
            onIrADetallesEjercicio = onIrADetallesEjercicio
        )
    }
}