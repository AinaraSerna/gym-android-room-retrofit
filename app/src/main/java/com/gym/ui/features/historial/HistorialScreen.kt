package com.gym.ui.features.historial

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun HistorialScreen(
    onIrAFormRegistrosPorFecha: (Int) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text("Historial")
    }
}