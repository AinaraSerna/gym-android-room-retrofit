package com.gym.ui.navigation.menulateral

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gym.ui.features.menulateral.ejercicios.EjerciciosApiScreen
import kotlinx.serialization.Serializable

@Serializable
data object EjerciciosApiRoute

fun NavGraphBuilder.ejerciciosApiDestination() {
    composable<EjerciciosApiRoute> {
        EjerciciosApiScreen()
    }
}