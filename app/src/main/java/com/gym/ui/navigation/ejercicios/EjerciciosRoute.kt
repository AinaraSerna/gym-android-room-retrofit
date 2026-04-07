package com.gym.ui.navigation.ejercicios

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gym.ui.features.ejercicios.EjerciciosScreen
import com.gym.ui.features.ejercicios.EjerciciosViewModel
import kotlinx.serialization.Serializable

@Serializable
data object EjerciciosRoute

fun NavGraphBuilder.ejerciciosDestination(
    ejerciciosVM: EjerciciosViewModel
) {
    composable<EjerciciosRoute> {
        EjerciciosScreen()
    }
}