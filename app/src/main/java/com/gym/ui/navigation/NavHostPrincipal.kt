package com.gym.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import com.gym.ui.composables.BarraInferior
import com.gym.ui.navigation.ejercicios.DetallesEjercicioRoute
import com.gym.ui.navigation.ejercicios.EjerciciosRoute
import com.gym.ui.navigation.ejercicios.detallesEjercicioDestination
import com.gym.ui.navigation.ejercicios.ejerciciosDestination
import com.gym.ui.navigation.historial.FormRegistrosPorFechaRoute
import com.gym.ui.navigation.historial.HistorialRoute
import com.gym.ui.navigation.historial.formRegistrosPorFechaDestination
import com.gym.ui.navigation.historial.historialDestination
import com.gym.ui.navigation.registros.FormNuevosRegistrosRoute
import com.gym.ui.navigation.registros.RegistrosRoute
import com.gym.ui.navigation.registros.formNuevosRegistrosDestination
import com.gym.ui.navigation.registros.registrosDestination
import com.gym.ui.navigation.sesiones.DetallesSesionRoute
import com.gym.ui.navigation.sesiones.SesionesRoute
import com.gym.ui.navigation.sesiones.detallesSesionDestination
import com.gym.ui.navigation.sesiones.sesionesDestination
import com.gym.ui.theme.RosaFucsia

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavHostPrincipal(
    navController: NavHostController
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val comportamientoAnteScroll = TopAppBarDefaults.pinnedScrollBehavior()
    val entradaEnPilaDeNavegacionActuasState by navController.currentBackStackEntryAsState()
    val iOpcionNavegacionSeleccionada by remember {
        derivedStateOf {
            when {
                entradaEnPilaDeNavegacionActuasState == null -> 0
                entradaEnPilaDeNavegacionActuasState!!.destination.hasRoute<RegistrosRoute>() -> 0
                entradaEnPilaDeNavegacionActuasState!!.destination.hasRoute<HistorialRoute>() -> 1
                entradaEnPilaDeNavegacionActuasState!!.destination.hasRoute<EjerciciosRoute>() -> 2
                entradaEnPilaDeNavegacionActuasState!!.destination.hasRoute<SesionesRoute>() -> 3
                else -> 0
            }
        }
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Top App Bar provisional") },
                scrollBehavior = comportamientoAnteScroll,
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = RosaFucsia,
                    titleContentColor = Color.White
                )
            )
        },
        bottomBar = {
            BarraInferior(
                iOpcionSeleccionada = iOpcionNavegacionSeleccionada,
                onNavegarAPantalla = { indice -> navegarAOpcion(navController, indice) }
            )
        },
        floatingActionButton = { },
        floatingActionButtonPosition = FabPosition.End,
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = RegistrosRoute,
            modifier = Modifier.padding(paddingValues).background(Color.White)
        ) {
            // Pantallas principales (en bottom bar)
            registrosDestination(
                onIrAFormNuevosRegistros = {
                    navController.navigate(route = FormNuevosRegistrosRoute)
                }
            )
            historialDestination(
                onIrAFormRegistrosPorFecha = {
                    navController.navigate(route = FormRegistrosPorFechaRoute)
                }
            )
            ejerciciosDestination(
                onIrADetallesEjercicio = {
                    navController.navigate(route = DetallesEjercicioRoute)
                }
            )
            sesionesDestination(
                onIrADetallesSesion = {
                    navController.navigate(route = DetallesSesionRoute)
                }
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
        }
    }
}