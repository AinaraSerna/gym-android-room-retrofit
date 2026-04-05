package com.gym.ui.navigation.sesiones

import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gym.ui.features.sesiones.SesionScreen
import com.gym.ui.features.sesiones.SesionesViewModel
import kotlinx.serialization.Serializable

@Serializable
data object SesionesRoute

fun NavGraphBuilder.sesionesDestination(
    sesionesVM: SesionesViewModel
) {
    composable<SesionesRoute> {
        val sesiones by sesionesVM.sesiones.collectAsStateWithLifecycle()
        val sesionSeleccionada by sesionesVM.sesionSeleccionada.collectAsStateWithLifecycle()
        SesionScreen(
            sesiones = sesiones,
            sesionSeleccionada = sesionSeleccionada,
            onSesionEvent = sesionesVM::onSesionEvent
        )
    }
}