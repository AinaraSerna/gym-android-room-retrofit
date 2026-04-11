package com.gym.ui.features.registros

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
import androidx.compose.ui.unit.dp
import com.gym.ui.features.sesiones.SesionEvent
import com.gym.ui.features.sesiones.SesionUiState
import com.gym.ui.theme.Cereza
import com.gym.ui.theme.RosaPalo
import com.gym.ui.theme.RosaRojo

@Composable
fun RegistrosScreen(
    sesiones: List<SesionUiState>,
    sesionSeleccionada: SesionUiState?,
    onRegistroEvent: (RegistrosEvent) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(25.dp),
            contentPadding = PaddingValues(18.dp)
        ) {
            items(sesiones) { sesion ->
                val estaSeleccionada = sesion.id == sesionSeleccionada?.id
                ElevatedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .border(
                            width = 1.dp,
                            color = if (estaSeleccionada) Cereza else Cereza.copy(alpha = 0.2f),
                            shape = MaterialTheme.shapes.medium
                        ),
                    onClick = {
                        if (estaSeleccionada) {
                            onRegistroEvent(RegistrosEvent.OnGetSesionById(null))
                        } else {
                            onRegistroEvent(RegistrosEvent.OnGetSesionById(sesion.id))
                        }
                    },
                    colors = CardDefaults.elevatedCardColors(
                        containerColor = RosaPalo
                    ),
                    shape = MaterialTheme.shapes.medium,
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = if (estaSeleccionada) 9.dp else 3.dp
                    )
                ) {
                    Row(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Box {
                            // Barra de acento lateral
                            Box(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .width(if (estaSeleccionada) 8.dp else 4.dp)
                                    .background(
                                        color = if (estaSeleccionada) Cereza else Cereza.copy(
                                            alpha = 0.3f
                                        )
                                    )
                            )

                            Column(
                                modifier = Modifier
                                    .padding(horizontal = 16.dp, vertical = 12.dp)
                                    .fillMaxSize(),
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = sesion.nombre,
                                    style = MaterialTheme.typography.titleLarge,
                                    color = Cereza,
                                    fontWeight = FontWeight.ExtraBold
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = sesion.descripcion,
                                    color = RosaRojo,
                                    style = MaterialTheme.typography.bodySmall,
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }

                            // Icono de flecha para indicar interactividad
                            Icon(
                                imageVector = Icons.Default.ChevronRight,
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(end = 12.dp)
                                    .align(Alignment.CenterEnd),
                                tint = Cereza.copy(alpha = 0.4f)
                            )

                            if (estaSeleccionada) {
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