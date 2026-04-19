package com.gym.ui.navigation.registros

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gym.ui.features.historial.HistorialEvent
import com.gym.ui.features.registros.formNuevosRegistros.FormNuevosRegistrosScreen
import com.gym.ui.features.registros.formNuevosRegistros.FormNuevosRegistrosViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.serialization.Serializable

@Serializable
data class FormNuevosRegistrosRoute(val codSesion : Int)

fun NavGraphBuilder.formNuevosRegistrosDestination(
    onIrAtras: () -> Unit,
    scope: CoroutineScope,
    snackbarHostState: SnackbarHostState,
    onHistorialEvent: (HistorialEvent) -> Unit
) {
    composable<FormNuevosRegistrosRoute> {
        val formNuevosRegistrosVM = hiltViewModel<FormNuevosRegistrosViewModel>()
        FormNuevosRegistrosScreen(
            onIrAtras = onIrAtras,
            listaEjercicios = formNuevosRegistrosVM.listaEjerciciosDeSesion.collectAsState().value,
            scope = scope,
            snackbarHostState = snackbarHostState,
            onRegistrosEvent = formNuevosRegistrosVM::onRegistrosEvent,
            onHistorialEvent = onHistorialEvent,
            codSesion = formNuevosRegistrosVM.codSesion,
            ultimosRegistros = formNuevosRegistrosVM.ultimosRegistros.collectAsState().value
        )
    }
}
