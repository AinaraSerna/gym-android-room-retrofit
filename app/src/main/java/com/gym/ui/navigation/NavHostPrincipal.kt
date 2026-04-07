package com.gym.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import com.gym.ui.composables.BarraInferior
import com.gym.ui.composables.BarraSuperior
import com.gym.ui.composables.BotonFlotante
import com.gym.ui.composables.dialogos.DialogoEliminarSesion
import com.gym.ui.composables.dialogos.InsertarEjercicioDialogo
import com.gym.ui.composables.dialogos.InsertarSesionDialogo
import com.gym.ui.features.ejercicios.EjerciciosViewModel
import com.gym.ui.features.sesiones.SesionUiState
import com.gym.ui.features.sesiones.SesionesViewModel
import com.gym.ui.navigation.ejercicios.DetallesEjercicioRoute
import com.gym.ui.navigation.ejercicios.EjerciciosRoute
import com.gym.ui.navigation.ejercicios.detallesEjercicioDestination
import com.gym.ui.navigation.ejercicios.ejerciciosDestination
import com.gym.ui.navigation.historial.FormRegistrosPorFechaRoute
import com.gym.ui.navigation.historial.HistorialRoute
import com.gym.ui.navigation.historial.formRegistrosPorFechaDestination
import com.gym.ui.navigation.historial.historialDestination
import com.gym.ui.navigation.menulateral.EjerciciosApiRoute
import com.gym.ui.navigation.menulateral.RegistrosApiRoute
import com.gym.ui.navigation.menulateral.SesionesApiRoute
import com.gym.ui.navigation.menulateral.ejerciciosApiDestination
import com.gym.ui.navigation.menulateral.registrosApiDestination
import com.gym.ui.navigation.menulateral.sesionesApiDestination
import com.gym.ui.navigation.registros.FormNuevosRegistrosRoute
import com.gym.ui.navigation.registros.RegistrosRoute
import com.gym.ui.navigation.registros.formNuevosRegistrosDestination
import com.gym.ui.navigation.registros.registrosDestination
import com.gym.ui.navigation.sesiones.DetallesSesionRoute
import com.gym.ui.navigation.sesiones.SesionesRoute
import com.gym.ui.navigation.sesiones.detallesSesionDestination
import com.gym.ui.navigation.sesiones.sesionesDestination
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavHostPrincipal(
    navController: NavHostController,
    onAbrirMenuLateral: () -> Unit,
    scope: CoroutineScope
) {
    val comportamientoAnteScroll = TopAppBarDefaults.pinnedScrollBehavior()
    val snackbarHostState = remember { SnackbarHostState() }
    val entradaEnPilaDeNavegacionActuasState by navController.currentBackStackEntryAsState()

    val iOpcionNavegacionSeleccionada by remember {
        derivedStateOf {
            val destino = entradaEnPilaDeNavegacionActuasState?.destination
            when {
                destino == null -> -1
                destino.hasRoute<RegistrosRoute>() -> 0
                destino.hasRoute<HistorialRoute>() -> 1
                destino.hasRoute<EjerciciosRoute>() -> 2
                destino.hasRoute<SesionesRoute>() -> 3
                destino.hasRoute<FormNuevosRegistrosRoute>() -> 4
                destino.hasRoute<FormRegistrosPorFechaRoute>() -> 5
                destino.hasRoute<DetallesEjercicioRoute>() -> 6
                destino.hasRoute<DetallesSesionRoute>() -> 7
                destino.hasRoute<SesionesApiRoute>() -> 8
                destino.hasRoute<EjerciciosApiRoute>() -> 9
                destino.hasRoute<RegistrosApiRoute>() -> 10
                else -> -1
            }
        }
    }
    val sesionesVM = hiltViewModel<SesionesViewModel>()
    val ejerciciosVM = hiltViewModel<EjerciciosViewModel>()

    val (mostrarDialogoInsertarSesion, setMostrarDialogoInsertarSesion) = remember {
        mutableStateOf(value = false)
    }
    val (mostrarDialogoEliminarSesion, setMostrarDialogoEliminarSesion) = remember {
        mutableStateOf(value = false)
    }
    val (mostrarDialogoInsertarEjercicio, setMostrarDialogoInsertarEjercicio) = remember {
        mutableStateOf(value = false)
    }
    val (mostrarDialogoEliminarEjercicio, setMostrarDialogoEliminarEjercicio) = remember {
        mutableStateOf(value = false)
    }

    Scaffold(
        topBar = {
            BarraSuperior(
                comportamientoAnteScroll = comportamientoAnteScroll,
                onAbrirMenuLateral = onAbrirMenuLateral,
                titulo = when (iOpcionNavegacionSeleccionada) {
                    0 -> "Registros"
                    1 -> "Historial"
                    2 -> "Ejercicios"
                    3 -> "Sesiones"
                    4 -> "Formulario registros"
                    5 -> "Formulario historial"
                    6 -> "Detalles ejercicio"
                    7 -> "Detalles sesión"
                    8 -> "Sesiones API"
                    9 -> "Ejercicios API"
                    10 -> "Registros API"
                    else -> "Gym"
                },
                opcionSeleccionada = when (iOpcionNavegacionSeleccionada) {
                    2 -> ejerciciosVM.ejercicioSeleccionado.collectAsState().value != null
                    3 -> sesionesVM.sesionSeleccionada.collectAsState().value != null
                    else -> false
                },
                setMostrarDialogoEliminacion = when (iOpcionNavegacionSeleccionada) {
                    2 -> setMostrarDialogoEliminarEjercicio
                    3 -> setMostrarDialogoEliminarSesion
                    else -> { _ -> }
                },
                iOpcionSeleccionada = iOpcionNavegacionSeleccionada,
                onSesionEvent = sesionesVM::onSesionEvent,
                navController = navController
            )
        },
        bottomBar = {
            BarraInferior(
                iOpcionSeleccionada = iOpcionNavegacionSeleccionada,
                onNavegarAPantalla = { indice ->
                    navegarAOpcionBarraInferior(
                        navController = navController,
                        indice = indice
                    )
                }
            )
        },
        floatingActionButton = {
            BotonFlotante(
                onMostrarDialogo = {
                    when (iOpcionNavegacionSeleccionada) {
                        2 -> setMostrarDialogoInsertarEjercicio(it)
                        3 -> setMostrarDialogoInsertarSesion(it)
                        else -> {}
                    }
                }
            )
        },
        floatingActionButtonPosition = FabPosition.End,
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { paddingValues ->
        // Diálogos
        // Sesiones
        if (mostrarDialogoInsertarSesion) {
            InsertarSesionDialogo(
                setMostrarDialogoInsertarSesion = setMostrarDialogoInsertarSesion,
                sesiones = sesionesVM.sesiones.collectAsState().value,
                onSesionEvent = sesionesVM::onSesionEvent,
                scope = scope,
                snackbarHostState = snackbarHostState
            )
        }
        if (mostrarDialogoEliminarSesion) {
            DialogoEliminarSesion(
                setMostrarDialogo = setMostrarDialogoEliminarSesion,
                snackbarHostState = snackbarHostState,
                scope = scope,
                onSesionEvent = sesionesVM::onSesionEvent,
                sesionSeleccionada = sesionesVM.sesionSeleccionada.collectAsState().value
                    ?: SesionUiState()
            )
        }
        // Ejercicios
        if (mostrarDialogoInsertarEjercicio) {
            val sesionesDesplegable = listOf(Pair(null, " --- ")) +
                        sesionesVM.sesiones.collectAsState().value.map { Pair(it.id, it.nombre) }
            InsertarEjercicioDialogo(
                setMostrarDialogoInsertarEjercicio = setMostrarDialogoInsertarEjercicio,
                ejercicios = ejerciciosVM.ejercicios.collectAsState().value.map { it.nombre },
                sesiones = sesionesDesplegable,
                onEjercicioEvent = ejerciciosVM::onEjercicioEvent,
                scope = scope,
                snackbarHostState = snackbarHostState
            )
        }
        NavHost(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color.White),
            navController = navController,
            startDestination = RegistrosRoute
        ) {
            // Pantallas principales
            registrosDestination(
                onIrAFormNuevosRegistros = { navController.navigate(FormNuevosRegistrosRoute) }
            )
            historialDestination(
                onIrAFormRegistrosPorFecha = { navController.navigate(FormRegistrosPorFechaRoute) }
            )
            ejerciciosDestination(
                ejerciciosVM = ejerciciosVM
            )
            sesionesDestination(
                sesionesVM = sesionesVM
            )

            // Pantallas secundarias
            formNuevosRegistrosDestination(
                onIrAtras = { navController.popBackStack() },
                scope = scope,
                snackbarHostState = snackbarHostState
            )
            formRegistrosPorFechaDestination(
                onIrAtras = { navController.popBackStack() },
                scope = scope,
                snackbarHostState = snackbarHostState
            )
            detallesEjercicioDestination(
                ejerciciosVM = ejerciciosVM,
                onIrAtras = { navController.popBackStack() },
                scope = scope,
                snackbarHostState = snackbarHostState
            )
            detallesSesionDestination(
                sesionesVM = sesionesVM,
                onIrAtras = { navController.popBackStack() },
                scope = scope,
                snackbarHostState = snackbarHostState
            )

            // Opciones menú lateral
            registrosApiDestination()
            ejerciciosApiDestination()
            sesionesApiDestination()
        }
    }
}