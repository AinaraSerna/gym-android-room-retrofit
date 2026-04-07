package com.gym.ui.navigation.ejercicios

import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gym.ui.features.ejercicios.EjerciciosViewModel
import com.gym.ui.features.ejercicios.detallesEjercicio.DetallesEjercicioScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.serialization.Serializable

@Serializable
data object DetallesEjercicioRoute

fun NavGraphBuilder.detallesEjercicioDestination(
    onIrAtras: () -> Unit,
    ejerciciosVM: EjerciciosViewModel,
    scope: CoroutineScope,
    snackbarHostState: SnackbarHostState
) {
    composable<DetallesEjercicioRoute> {
        DetallesEjercicioScreen(
            onIrAtras = onIrAtras
        )
    }
}