package com.gym.ui.navigation.sesiones

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gym.ui.features.sesiones.detallesSesion.DetallesSesionScreen
import kotlinx.serialization.Serializable

@Serializable
data object DetallesSesionRoute

fun NavGraphBuilder.detallesSesionDestination() {
    composable<DetallesSesionRoute> {
        DetallesSesionScreen()
    }
}