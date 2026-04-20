package com.gym.ui.composables.dialogos

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gym.ui.features.historial.HistorialEvent
import com.gym.ui.features.sesiones.SesionUiState
import com.gym.ui.theme.Cereza

@Composable
fun DialogoFiltrarHistorial(
    onMostrarDialogo: (Boolean) -> Unit,
    onHistorailEvent: (HistorialEvent) -> Unit,
    sesiones: List<SesionUiState>
) {
    val (opcionSeleccionada, onOpcionSeleccionada) = remember {
        mutableStateOf(value = sesiones.first())
    }
    AlertDialog(
        onDismissRequest = {},
        title = {
            Text(text = "Filtrar historial", color = Cereza, fontWeight = FontWeight.Bold)
        },
        text = {
            Column(Modifier.selectableGroup()) {
                sesiones.forEach { sesion ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .selectable(
                                selected = (sesion == opcionSeleccionada),
                                onClick = { onOpcionSeleccionada(sesion) },
                                role = Role.RadioButton
                            )
                            .padding(all = 5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            modifier = Modifier.padding(end = 8.dp),
                            selected = (sesion == opcionSeleccionada),
                            onClick = null,
                            colors = RadioButtonDefaults.colors(
                                selectedColor = Cereza,
                                unselectedColor = Color.Black.copy(alpha = 0.8f)
                            )
                        )
                        Text(
                            text = sesion.nombre,
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = if (sesion == opcionSeleccionada) FontWeight.Bold else FontWeight.Normal
                        )
                    }
                }
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onHistorailEvent(HistorialEvent.OnFiltrarHistorial(codSesion = opcionSeleccionada.id!!))
                    onMostrarDialogo(false)
                }
            ) {
                Text(text = "Aceptar", color = Cereza)
            }
        },
        dismissButton = {
            TextButton(
                onClick = { onMostrarDialogo(false) }
            ) {
                Text(text = "Cancelar", color = Cereza)
            }
        },
        containerColor = Color.White
    )
}

@Preview(showBackground = true)
@Composable
fun DialogoFiltrarHistorialPreview() {
    DialogoFiltrarHistorial(
        onMostrarDialogo = {},
        onHistorailEvent = {},
        sesiones = listOf(
            SesionUiState(id = 1, nombre = "1-M"),
            SesionUiState(id = 2, nombre = "2-J"),
            SesionUiState(id = 3, nombre = "3-S")
        )
    )
}