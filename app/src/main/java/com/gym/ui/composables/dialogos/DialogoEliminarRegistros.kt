package com.gym.ui.composables.dialogos

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Checklist
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.gym.ui.composables.SnackbarMensaje
import com.gym.ui.features.historial.HistorialEvent
import com.gym.ui.theme.Cereza
import com.gym.ui.theme.RosaRojo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialogoEliminarRegistros(
    setMostrarDialogo: (Boolean) -> Unit,
    snackbarHostState: SnackbarHostState,
    scope: CoroutineScope,
    onHistorialEvent: (HistorialEvent) -> Unit,
    fechaRegistroHistorialSeleccionado: String
) {
    AlertDialog(
        onDismissRequest = {},
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    modifier = Modifier.padding(end = 6.dp),
                    imageVector = Icons.Filled.Checklist,
                    contentDescription = null,
                    tint = Cereza
                )
                Text(
                    text = "Borrar registros",
                    fontWeight = FontWeight.Bold,
                    color = Cereza
                )
            }
        },
        text = {
            Text(
                text = "¿Estás segura que quieres eliminar los registros del ${fechaRegistroHistorialSeleccionado}?",
                color = RosaRojo
            )
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onHistorialEvent(HistorialEvent.OnDeleteRegistros)
                    setMostrarDialogo(false)
                    scope.launch {
                        SnackbarMensaje(
                            mensaje = "Registros eliminados correctamente",
                            snackbarHostState = snackbarHostState
                        )
                    }
                }
            ) {
                Text(text = "Eliminar", color = Cereza)
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onHistorialEvent(HistorialEvent.OnGetEntradaDelHistorial(fecha = null))
                    setMostrarDialogo(false)
                }
            ) {
                Text(text = "Cancelar", color = Cereza)
            }
        },
        containerColor = Color.White
    )
}