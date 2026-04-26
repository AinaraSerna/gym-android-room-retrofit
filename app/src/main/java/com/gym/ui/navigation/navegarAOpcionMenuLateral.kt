package com.gym.ui.navigation

import androidx.navigation.NavController
import com.gym.ui.navigation.menulateral.EjerciciosApiRoute
import com.gym.ui.navigation.menulateral.HistorialApiRoute
import com.gym.ui.navigation.menulateral.SesionesApiRoute

fun navegarAOpcionMenuLateral(navController: NavController, indice: Int) {
    val ruta = when (indice) {
        0 -> SesionesApiRoute
        1 -> EjerciciosApiRoute
        2 -> HistorialApiRoute
        else -> return
    }
    
    navController.navigate(route = ruta) {
        val startRoute = navController.graph.startDestinationRoute
        if (startRoute != null) {
            popUpTo(startRoute) {
                saveState = true
            }
        }
        launchSingleTop = true
        restoreState = true
    }
}
