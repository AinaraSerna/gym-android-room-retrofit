package com.gym.ui.features.historial

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gym.ui.theme.Cereza
import com.gym.ui.theme.CerezaOscuro
import com.gym.ui.theme.RosaPalo
import com.gym.ui.theme.RosaRojo
import com.gym.ui.utils.fechaLargaFormatoHispano
import java.time.LocalDate

@Composable
fun HistorialScreen(
    historial: List<HistorialUiState>,
    historialSeleccionado: HistorialUiState?,
    onHistorialEvent: (HistorialEvent) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(25.dp),
            contentPadding = PaddingValues(18.dp)
        ) {
            items(historial) { registroDeHistorial ->
                val estaSeleccionado = registroDeHistorial == historialSeleccionado
                ElevatedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .border(
                            width = 1.dp,
                            color = if (estaSeleccionado) Cereza else Cereza.copy(alpha = 0.2f),
                            shape = MaterialTheme.shapes.medium
                        ),
                    onClick = {
                        if (estaSeleccionado) {
                            onHistorialEvent(HistorialEvent.OnGetHistorialById(id = null))
                        } else {
                            onHistorialEvent(
                                HistorialEvent.OnGetHistorialById(registroDeHistorial.id)
                            )
                        }
                    },
                    colors = CardDefaults.elevatedCardColors(
                        containerColor = RosaPalo
                    ),
                    elevation = CardDefaults.elevatedCardElevation(
                        defaultElevation = if (estaSeleccionado) 9.dp else 3.dp
                    )
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxHeight()
                                .width(if (estaSeleccionado) 8.dp else 4.dp)
                                .background(
                                    color = if (estaSeleccionado) Cereza else Cereza.copy(alpha = 0.3f)
                                )
                        )
                        Box(modifier = Modifier.fillMaxSize()) {
                            Column(
                                modifier = Modifier
                                    .padding(horizontal = 16.dp, vertical = 12.dp)
                                    .fillMaxSize(),
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = fechaLargaFormatoHispano(fecha = registroDeHistorial.fecha),
                                    style = MaterialTheme.typography.titleMedium,
                                    color = CerezaOscuro,
                                    fontWeight = FontWeight.ExtraBold
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = registroDeHistorial.codSesion.toString(),
                                    color = RosaRojo,
                                    style = MaterialTheme.typography.bodyMedium,
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }

                            Icon(
                                imageVector = Icons.Default.ChevronRight,
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(end = 12.dp)
                                    .align(Alignment.CenterEnd),
                                tint = Cereza.copy(alpha = 0.7f)
                            )

                            if (estaSeleccionado) {
                                Box(
                                    modifier = Modifier
                                        .matchParentSize()
                                        .background(color = Cereza.copy(alpha = 0.4f))
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HistorialScreenPreview() {
    val historial = listOf(
        HistorialUiState(
            codSesion = 2,
            fecha = LocalDate.now()
        ),
        HistorialUiState(
            codSesion = 1,
            fecha = LocalDate.now().minusDays(3)
        )
    )
    HistorialScreen(
        historial = historial,
        historialSeleccionado = null,
        onHistorialEvent = {}
    )
}