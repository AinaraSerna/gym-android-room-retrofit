package com.gym.ui.navigation.registros

import androidx.compose.runtime.collectAsState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gym.ui.features.registros.RegistrosScreen
import com.gym.ui.features.registros.RegistrosViewModel
import kotlinx.serialization.Serializable

@Serializable
data object RegistrosRoute

fun NavGraphBuilder.registrosDestination(
    registrosVM: RegistrosViewModel
) {
    composable<RegistrosRoute> {
        val sesiones = registrosVM.sesiones.collectAsState().value

        RegistrosScreen(
            sesiones = sesiones,
            sesionSeleccionada = registrosVM.sesionRegistrosSeleccionada.collectAsState().value,
            onRegistroEvent = registrosVM::onRegistrosEvent
        )
    }
}