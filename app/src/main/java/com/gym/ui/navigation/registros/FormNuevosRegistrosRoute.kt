package com.gym.ui.navigation.registros

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gym.ui.features.registros.formNuevosRegistros.FormNuevosRegistrosScreen
import kotlinx.serialization.Serializable

@Serializable
data object FormNuevosRegistrosRoute

fun NavGraphBuilder.formNuevosRegistrosDestination(
    onIrAtras : () -> Unit
) {
    composable<FormNuevosRegistrosRoute> {
        FormNuevosRegistrosScreen(
            onIrAtras = onIrAtras
        )
    }
}
