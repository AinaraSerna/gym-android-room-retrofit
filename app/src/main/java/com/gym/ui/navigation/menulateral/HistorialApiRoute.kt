package com.gym.ui.navigation.menulateral

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gym.ui.features.menulateral.historial.HistorialApiScreen
import kotlinx.serialization.Serializable

@Serializable
data object HistorialApiRoute

fun NavGraphBuilder.historialApiDestination() {
    composable<HistorialApiRoute>{
        HistorialApiScreen()
    }
}