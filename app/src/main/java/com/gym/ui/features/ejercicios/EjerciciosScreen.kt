package com.gym.ui.features.ejercicios

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gym.ui.features.sesiones.SesionUiState
import com.gym.ui.theme.Cereza

@Composable
fun EjerciciosScreen(
    ejercicios: List<EjercicioUiState>,
    onEjercicioEvent: (EjercicioEvent) -> Unit,
    sesiones: List<SesionUiState>
) {
//    val scrollState = rememberScrollState()
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 16.dp)
//            .verticalScroll(scrollState)
    ) {
        items(sesiones) { sesion ->
            Column {
                val ejerciciosDeSesion = ejercicios.filter { ej -> ej.codSesion == sesion.id }
                Text(text = sesion.nombre)
                Text(text = "Total ejercicios: ${ejerciciosDeSesion.size}")
                HorizontalDivider(color = Cereza.copy(alpha = 0.3f))
                ejerciciosDeSesion.forEach { ejercicio ->
                    Text(text = "\t· ${ejercicio.orden} - ${ejercicio.nombre}")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EjerciciosScreenPreview() {
    EjerciciosScreen(
        ejercicios = listOf(
            EjercicioUiState(
                id = 1,
                nombre = "Pecho plano",
                orden = 1,
                codSesion = 1,
                notas = "Una nota"
            ),
            EjercicioUiState(
                id = 2,
                nombre = "Pecho inclinado",
                orden = 2,
                codSesion = 1,
                notas = "Otra nota"
            ),
            EjercicioUiState(
                id = 3,
                nombre = "Pecho plano",
                orden = 1,
                codSesion = 2,
                notas = "Una nota"
            )
        ),
        onEjercicioEvent = {},
        sesiones = listOf(
            SesionUiState(
                id = 1,
                nombre = "Sesion 1"
            ),
            SesionUiState(
                id = 2,
                nombre = "Sesion 2"
            )
        )
    )
}