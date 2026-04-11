package com.gym.ui.features.registros.formNuevosRegistros

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.gym.ui.features.ejercicios.EjercicioUiState
import kotlinx.coroutines.CoroutineScope

@Composable
fun FormNuevosRegistrosScreen(
    onIrAtras: () -> Unit,
    scope: CoroutineScope,
    snackbarHostState: SnackbarHostState,
    listaEjercicios: List<EjercicioUiState>
) {
    Column {
        listaEjercicios.forEach {
            Text(text = it.nombre)
        }
    }
}