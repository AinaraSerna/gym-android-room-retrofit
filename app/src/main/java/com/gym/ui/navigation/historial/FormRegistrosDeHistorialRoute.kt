package com.gym.ui.navigation.historial

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gym.ui.features.historial.HistorialViewModel
import com.gym.ui.features.historial.formRegistrosDeHistorial.FormRegistrosDeHistorial
import com.gym.ui.features.historial.formRegistrosDeHistorial.FormRegistrosEnHistorialViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.serialization.Serializable

@Serializable
data class FormRegistrosDeHistorial(val codHistorial: Int, val codSesion : Int)

fun NavGraphBuilder.formRegistrosDeHistorialDestination(
    onIrAtras: () -> Unit,
    scope: CoroutineScope,
    snackbarHostState: SnackbarHostState,
    historialVM: HistorialViewModel
) {
    composable<FormRegistrosDeHistorial> {
        val formRegistrosDeHistorialVM = hiltViewModel<FormRegistrosEnHistorialViewModel>()
        val listaEjercicios = formRegistrosDeHistorialVM.listaEjercicios.collectAsState().value
        val datosPeso = formRegistrosDeHistorialVM.datosPeso.collectAsState().value
        val datosReps = formRegistrosDeHistorialVM.datosReps.collectAsState().value
        val algunCambio = formRegistrosDeHistorialVM.algunCambio.collectAsState().value

        FormRegistrosDeHistorial (
            onIrAtras = onIrAtras,
            scope = scope,
            snackbarHostState = snackbarHostState,
            listaEjercicios = listaEjercicios,
            datosPeso = datosPeso,
            datosReps = datosReps,
            algunCambio = algunCambio,
            onHistorialEvent = historialVM::onHistorialEvent,
            onRegistrosEnHistorialEvent = formRegistrosDeHistorialVM::onRegistrosEnHistorialEvent
        )
    }
}