package com.gym.ui.navigation.ejercicios

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gym.ui.features.ejercicios.EjerciciosScreen
import com.gym.ui.features.ejercicios.EjerciciosViewModel
import com.gym.ui.features.sesiones.SesionesViewModel
import kotlinx.serialization.Serializable

@Serializable
data object EjerciciosRoute

fun NavGraphBuilder.ejerciciosDestination(
    ejerciciosVM: EjerciciosViewModel,
    sesionesVM: SesionesViewModel
) {
    composable<EjerciciosRoute> {
        val sesiones by sesionesVM.sesiones.collectAsState()
        val ejercicios by ejerciciosVM.ejercicios.collectAsState()
        val ejercicioSeleccionado by ejerciciosVM.ejercicioSeleccionado.collectAsState()
        
        EjerciciosScreen(
            ejercicios = ejercicios,
            onEjercicioEvent = ejerciciosVM::onEjercicioEvent,
            sesiones = sesiones,
            ejercicioSeleccionado = ejercicioSeleccionado
        )
    }
}