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
import com.gym.ui.composables.dialogos.InsertarSesionDialogo
import com.gym.ui.features.sesiones.SesionesViewModel
import com.gym.ui.navigation.ejercicios.DetallesEjercicioRoute
import com.gym.ui.navigation.ejercicios.EjerciciosRoute
import com.gym.ui.navigation.ejercicios.detallesEjercicioDestination
import com.gym.ui.navigation.ejercicios.ejerciciosDestination
import com.gym.ui.navigation.historial.FormRegistrosPorFechaRoute
import com.gym.ui.navigation.historial.HistorialRoute
import com.gym.ui.navigation.historial.formRegistrosPorFechaDestination
import com.gym.ui.navigation.historial.historialDestination
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
                else -> -1
            }
        }
    }
    val sesionesVM = hiltViewModel<SesionesViewModel>()

    val (mostrarDialogoInsertarSesion, setMostrarDialogoInsertarSesion) = remember {
        mutableStateOf(value = false)
    }

    Scaffold(
        topBar = {
            BarraSuperior(comportamientoAnteScroll, onAbrirMenuLateral)
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
        if (mostrarDialogoInsertarSesion){
            InsertarSesionDialogo(
                setMostrarDialogoInsertarSesion = setMostrarDialogoInsertarSesion,
                sesiones = sesionesVM.sesiones.collectAsState().value
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
                onIrADetallesEjercicio = { navController.navigate(DetallesEjercicioRoute) }
            )
            sesionesDestination(
                onIrADetallesSesion = { navController.navigate(DetallesSesionRoute) },
                sesionesVM = sesionesVM
            )

            // Pantallas secundarias
            formNuevosRegistrosDestination(
                onIrAtras = { navController.popBackStack() }
            )
            formRegistrosPorFechaDestination(
                onIrAtras = { navController.popBackStack() }
            )
            detallesEjercicioDestination(
                onIrAtras = { navController.popBackStack() }
            )
            detallesSesionDestination(
                onIrAtras = { navController.popBackStack() }
            )

            // Opciones menú lateral
            registrosApiDestination()
            ejerciciosApiDestination()
            sesionesApiDestination()
        }
    }
}