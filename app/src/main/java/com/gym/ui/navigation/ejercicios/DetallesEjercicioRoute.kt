package com.gym.ui.navigation.ejercicios

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gym.ui.features.ejercicios.EjercicioUiState
import com.gym.ui.features.ejercicios.EjerciciosViewModel
import com.gym.ui.features.ejercicios.detallesEjercicio.DetallesEjercicioScreen
import com.gym.ui.features.sesiones.SesionesViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.serialization.Serializable

@Serializable
data object DetallesEjercicioRoute

fun NavGraphBuilder.detallesEjercicioDestination(
    onIrAtras: () -> Unit,
    ejerciciosVM: EjerciciosViewModel,
    scope: CoroutineScope,
    snackbarHostState: SnackbarHostState,
    sesionesVM: SesionesViewModel
) {
    composable<DetallesEjercicioRoute> {
        DetallesEjercicioScreen(
            onIrAtras = onIrAtras,
            listaSesiones = sesionesVM.sesiones.collectAsState().value,
            listaEjercicios = ejerciciosVM.ejercicios.collectAsState().value,
            ejercicioSeleccionado = ejerciciosVM.ejercicioSeleccionado.collectAsState().value
                ?: EjercicioUiState(),
            onEjercicioEvent = ejerciciosVM::onEjercicioEvent,
            scope = scope,
            snackbarHostState = snackbarHostState
        )
    }
}