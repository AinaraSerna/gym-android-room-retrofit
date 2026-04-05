package com.gym.ui.navigation.registros

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gym.ui.features.registros.RegistrosScreen
import kotlinx.serialization.Serializable

@Serializable
data object RegistrosRoute

fun NavGraphBuilder.registrosDestination(
    onIrAFormNuevosRegistros: () -> Unit
) {
    composable<RegistrosRoute> {
        RegistrosScreen(
            onIrAFormNuevosRegistros = onIrAFormNuevosRegistros
        )
    }
}