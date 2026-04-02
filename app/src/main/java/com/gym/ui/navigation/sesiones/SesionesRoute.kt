package com.gym.ui.navigation.sesiones

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gym.ui.features.sesiones.SesionScreen
import com.gym.ui.features.sesiones.SesionesViewModel
import kotlinx.serialization.Serializable

@Serializable
data object SesionesRoute

fun NavGraphBuilder.sesionesDestination(
    onIrADetallesSesion: (Int) -> Unit,
    sesionesVM: SesionesViewModel
) {
    composable<SesionesRoute> {
        val sesiones by sesionesVM.sesiones.collectAsState()
        val sesionSeleccionada by sesionesVM.sesionSeleccionada.collectAsState()
        SesionScreen(
            onIrADetallesSesion = onIrADetallesSesion,
            sesiones = sesiones,
            sesionSeleccionada = sesionSeleccionada
        )
    }
}