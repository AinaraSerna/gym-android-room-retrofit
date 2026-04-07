package com.gym.ui.navigation.registros

import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gym.ui.features.registros.formNuevosRegistros.FormNuevosRegistrosScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.serialization.Serializable

@Serializable
data object FormNuevosRegistrosRoute

fun NavGraphBuilder.formNuevosRegistrosDestination(
    onIrAtras: () -> Unit,
    scope: CoroutineScope,
    snackbarHostState: SnackbarHostState
) {
    composable<FormNuevosRegistrosRoute> {
        FormNuevosRegistrosScreen(
            onIrAtras = onIrAtras
        )
    }
}
