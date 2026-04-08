package com.gym.ui.features.ejercicios

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gym.ui.features.sesiones.SesionUiState
import com.gym.ui.theme.Cereza
import com.gym.ui.theme.RosaPalo
import com.gym.ui.theme.RosaRojo

@Composable
fun EjerciciosScreen(
    ejercicios: List<EjercicioUiState>,
    onEjercicioEvent: (EjercicioEvent) -> Unit,
    sesiones: List<SesionUiState>,
    ejercicioSeleccionado: EjercicioUiState?
) {
    val lazyState = rememberLazyListState()
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        state = lazyState,
        contentPadding = PaddingValues(all = 18.dp),
        verticalArrangement = Arrangement.spacedBy(25.dp),
    ) {
        items(sesiones) { sesion ->
            ElevatedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = 1.dp,
                        color = Cereza.copy(alpha = 0.2f),
                        shape = MaterialTheme.shapes.medium
                    ), colors = CardDefaults.elevatedCardColors(
                    containerColor = RosaPalo
                ), shape = MaterialTheme.shapes.medium, elevation = CardDefaults.cardElevation(
                    defaultElevation = 3.dp
                )
            ) {
                Column(
                    modifier = Modifier.padding(all = 12.dp)
                ) {
                    val ejerciciosDeSesion = ejercicios.filter { ej -> ej.codSesion == sesion.id }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 8.dp, end = 8.dp, top = 8.dp, bottom = 12.dp)
                    ) {
                        Text(
                            modifier = Modifier.align(Alignment.CenterStart),
                            text = sesion.nombre,
                            style = MaterialTheme.typography.titleLarge.copy(
                                shadow = Shadow(
                                    color = Color.Black.copy(alpha = 0.3f),
                                    offset = Offset(x = 4f, y = 4f),
                                    blurRadius = 2f
                                )
                            ),
                            color = Cereza,
                            fontWeight = FontWeight.ExtraBold
                        )
                        Box(
                            modifier = Modifier
                                .align(Alignment.CenterEnd)
                                .clip(shape = MaterialTheme.shapes.large)
                                .background(color = Cereza)
                                .padding(all = 1.dp),
                        ) {
                            Text(
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
                                text = "${ejerciciosDeSesion.size} ej.",
                                color = Color.White,
                                style = MaterialTheme.typography.labelSmall
                            )
                        }
                    }
                    HorizontalDivider(
                        color = Cereza.copy(alpha = 0.5f),
                        modifier = Modifier.padding(bottom = 3.dp)
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp)
                            .border(
                                width = 1.dp,
                                color = Cereza.copy(alpha = 0.5f),
                                shape = MaterialTheme.shapes.medium
                            )
                            .clip(shape = MaterialTheme.shapes.medium)
                    ) {
                        ejerciciosDeSesion.forEachIndexed { i, ejercicio ->
                            val estaSeleccionado = ejercicioSeleccionado?.id == ejercicio.id
                            Box(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(
                                            color = if (i % 2 == 0) RosaRojo.copy(alpha = 0.5f) else Color.White
                                        )
                                        .clickable(
                                            onClick = {
                                                if (estaSeleccionado) {
                                                    onEjercicioEvent(
                                                        EjercicioEvent.OnGetEjercicioById(null)
                                                    )
                                                } else {
                                                    onEjercicioEvent(
                                                        EjercicioEvent.OnGetEjercicioById(ejercicio.id)
                                                    )
                                                }
                                            })
                                        .padding(all = 12.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically) {
                                    Box(
                                        modifier = Modifier
                                            .weight(0.1f)
                                            .clip(shape = MaterialTheme.shapes.large)
                                            .background(color = if (i % 2 == 0) Color.White else Cereza)
                                            .padding(vertical = 1.dp, horizontal = 1.dp),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = ejercicio.orden.toString(),
                                            color = if (i % 2 == 0) Color.Black.copy(alpha = 0.8f) else Color.White,
                                            style = MaterialTheme.typography.titleLarge
                                        )
                                    }
                                    Column(
                                        modifier = Modifier
                                            .weight(0.8f)
                                            .padding(start = 10.dp)
                                    ) {
                                        Text(
                                            text = ejercicio.nombre,
                                            color = if (i % 2 == 0) Color.White else Color.Black.copy(
                                                alpha = 0.9f
                                            ),
                                            style = MaterialTheme.typography.bodyLarge,
                                            fontWeight = FontWeight.Bold
                                        )
                                        Text(
                                            text = ejercicio.notas,
                                            color = if (i % 2 == 0) Color.White.copy(alpha = 0.9f)
                                            else Color.Black.copy(alpha = 0.8f),
                                            style = MaterialTheme.typography.bodySmall
                                        )
                                    }
                                    Icon(
                                        imageVector = Icons.Filled.ChevronRight,
                                        contentDescription = null,
                                        tint = if (i % 2 == 0) Color.White else Color.Black.copy(
                                            alpha = 0.7f
                                        )
                                    )
                                }
                                if (i < ejerciciosDeSesion.size - 1) {
                                    HorizontalDivider(color = Cereza.copy(alpha = 0.1f))
                                }
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
}

@Preview(showBackground = true)
@Composable
fun EjerciciosScreenPreview() {
    EjerciciosScreen(
        ejercicios = listOf(
            EjercicioUiState(
                id = 1, nombre = "Pecho plano", orden = 1, codSesion = 1, notas = "Una nota"
            ), EjercicioUiState(
                id = 2, nombre = "Pecho inclinado", orden = 2, codSesion = 1, notas = "Otra nota"
            ), EjercicioUiState(
                id = 3, nombre = "Pecho plano", orden = 1, codSesion = 2, notas = "Una nota"
            )
        ), onEjercicioEvent = {}, sesiones = listOf(
            SesionUiState(
                id = 1, nombre = "1-M"
            ), SesionUiState(
                id = 2, nombre = "2-J"
            )
        ), ejercicioSeleccionado = null
    )
}