package com.gym.ui.navigation.registros

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gym.ui.features.registros.RegistrosScreen
import com.gym.ui.features.sesiones.SesionUiState
import kotlinx.serialization.Serializable

@Serializable
data object RegistrosRoute

fun NavGraphBuilder.registrosDestination(
    onIrAFormNuevosRegistros: () -> Unit,
    sesiones: List<SesionUiState>
) {
    composable<RegistrosRoute> {
        RegistrosScreen(
            onIrAFormNuevosRegistros = onIrAFormNuevosRegistros,
            sesiones = sesiones
        )
    }
}