package com.gym.ui.features.sesiones

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.gym.ui.theme.Cereza
import com.gym.ui.theme.RosaPalo
import com.gym.ui.theme.RosaRojo

@Composable
fun SesionScreen(
    sesiones: List<SesionUiState>,
    sesionSeleccionada: SesionUiState?,
    onSesionEvent: (SesionEvent) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(sesiones) { sesion ->
                val estaSeleccionada = sesionSeleccionada?.id == sesion.id
                ElevatedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 16.dp)
                        .height(120.dp)
                        .border(
                            width = if (estaSeleccionada) 5.dp else 3.dp,
                            color = Cereza,
                            shape = MaterialTheme.shapes.extraSmall
                        ),
                    onClick = {
                        if (estaSeleccionada) {
                            onSesionEvent(SesionEvent.OnGetSesionById(null))
                        } else {
                            onSesionEvent(SesionEvent.OnGetSesionById(sesion.id))
                        }
                    },
                    colors = CardDefaults.elevatedCardColors(
                        containerColor = RosaPalo,
                        contentColor = Color.White
                    ),
                    shape = MaterialTheme.shapes.extraSmall,
                    elevation = CardDefaults.elevatedCardElevation(
                        defaultElevation = if (estaSeleccionada) 18.dp else 12.dp
                    )
                ) {
                    Box {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(8.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                modifier = Modifier.padding(top = 4.dp, bottom = 6.dp),
                                text = sesion.nombre,
                                style = MaterialTheme.typography.titleLarge,
                                color = Cereza,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(
                                Modifier
                                    .fillMaxWidth()
                                    .background(Color.White)
                                    .size(1.dp)
                            )
                            Text(
                                modifier = Modifier.padding(top = 8.dp),
                                text = sesion.descripcion,
                                color = RosaRojo,
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                        if (estaSeleccionada) {
                            Box(
                                modifier = Modifier
                                    .matchParentSize()
                                    .background(color = Cereza.copy(alpha = 0.3f))
                            )
                        }
                    }
                }
            }
        }
    }
}