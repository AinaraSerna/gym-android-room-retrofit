package com.gym.ui.navigation.sesiones

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gym.ui.features.sesiones.SesionScreen
import kotlinx.serialization.Serializable

@Serializable
data object SesionesRoute

fun NavGraphBuilder.sesionesDestination(
    onIrADetallesSesion: (Int) -> Unit
) {
    composable<SesionesRoute> {
        SesionScreen(
            onIrADetallesSesion = onIrADetallesSesion
        )
    }
}