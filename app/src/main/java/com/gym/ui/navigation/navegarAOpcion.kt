package com.gym.ui.navigation

import androidx.navigation.NavController
import com.gym.ui.navigation.ejercicios.EjerciciosRoute
import com.gym.ui.navigation.historial.HistorialRoute
import com.gym.ui.navigation.registros.RegistrosRoute
import com.gym.ui.navigation.sesiones.SesionesRoute

fun navegarAOpcion(navController: NavController, indice: Int) {
    val ruta = when (indice) {
        0 -> RegistrosRoute
        1 -> HistorialRoute
        2 -> EjerciciosRoute
        3 -> SesionesRoute
        else -> RegistrosRoute
    }
    navController.navigate(route = ruta) {
        popUpTo(navController.graph.startDestinationId) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}
