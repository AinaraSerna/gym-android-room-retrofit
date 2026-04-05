package com.gym.ui.navigation.sesiones

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gym.ui.features.sesiones.SesionUiState
import com.gym.ui.features.sesiones.SesionesViewModel
import com.gym.ui.features.sesiones.detallesSesion.DetallesSesionScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.serialization.Serializable

@Serializable
data object DetallesSesionRoute

fun NavGraphBuilder.detallesSesionDestination(
    sesionesVM: SesionesViewModel,
    onIrAtras: () -> Unit,
    scope: CoroutineScope,
    snackbarHostState: SnackbarHostState
) {
    composable<DetallesSesionRoute> {
        DetallesSesionScreen(
            sesionSeleccionada = sesionesVM.sesionSeleccionada.collectAsState().value
                ?: SesionUiState(),
            onSesionEvent = sesionesVM::onSesionEvent,
            sesiones = sesionesVM.sesiones.collectAsState().value,
            onIrAtras = onIrAtras,
            scope = scope,
            snackbarHostState = snackbarHostState
        )
    }
}