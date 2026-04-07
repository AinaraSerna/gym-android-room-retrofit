package com.gym.ui.features.ejercicios

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun EjerciciosScreen(
    ejercicios: List<EjercicioUiState>,
    onEjercicioEvent: (EjercicioEvent) -> Unit
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(ejercicios) { ejercicio ->
            Text(text = ejercicio.nombre)
        }
    }
}