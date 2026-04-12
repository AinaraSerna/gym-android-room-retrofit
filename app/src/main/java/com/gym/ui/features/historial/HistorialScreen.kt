package com.gym.ui.features.historial

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.gym.data.room.gym.registros.HistorialRegistro
import com.gym.ui.theme.RosaPalo
import com.gym.ui.utils.fechaFormatoHispano
import java.time.LocalDate

@Composable
fun HistorialScreen(
    onIrAFormRegistrosPorFecha: () -> Unit,
    historial: List<HistorialRegistro>,
    registroDeHistorialSeleccionado: HistorialRegistro?,
    onHistorialEvent: (HistorialEvent) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(historial) { registroDeHistorial ->
                val estaSeleccionado = registroDeHistorial == registroDeHistorialSeleccionado
                Text(
                    modifier = Modifier
                        .background(color = if (estaSeleccionado) RosaPalo else Color.White)
                        .clickable(
                        onClick = {
                            if (estaSeleccionado){
                                onHistorialEvent(HistorialEvent.OnGetRegistroDelHistorial(fecha = null))
                            } else {
                                onHistorialEvent(HistorialEvent.OnGetRegistroDelHistorial(registroDeHistorial.fecha))
                            }
                        }
                    ),
                    text = "${fechaFormatoHispano(registroDeHistorial.fecha)} - ${registroDeHistorial.nombreSesion}"
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HistorialScreenPreview() {
    HistorialScreen(
        onIrAFormRegistrosPorFecha = {},
        historial = listOf(
            HistorialRegistro(
                nombreSesion = "1-M",
                fecha = LocalDate.now()
            )
        ),
        registroDeHistorialSeleccionado = null,
        onHistorialEvent = {}
    )
}