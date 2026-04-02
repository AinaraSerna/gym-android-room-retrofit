package com.gym.ui.features.sesiones

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SesionScreen(
    onIrADetallesSesion: (Int) -> Unit,
    sesiones: List<SesionUiState>,
    sesionSeleccionada: SesionUiState?
) {
    Column(modifier = Modifier.fillMaxSize()) {
        sesiones.forEach { sesion ->
            Text(text = sesion.nombre)
        }
    }
}