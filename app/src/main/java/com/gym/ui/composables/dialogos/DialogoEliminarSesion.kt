package com.gym.ui.composables.dialogos

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ListAlt
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.gym.ui.composables.SnackbarMensaje
import com.gym.ui.features.sesiones.SesionEvent
import com.gym.ui.features.sesiones.SesionUiState
import com.gym.ui.theme.Cereza
import com.gym.ui.theme.RosaRojo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun DialogoEliminarSesion(
    setMostrarDialogo: (Boolean) -> Unit,
    snackbarHostState: SnackbarHostState,
    scope: CoroutineScope,
    onSesionEvent: (SesionEvent) -> Unit,
    sesionSeleccionada: SesionUiState
) {
    AlertDialog(
        onDismissRequest = {},
        title = {
            Row(
                verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
            ) {
                Icon(
                    modifier = Modifier.padding(end = 6.dp),
                    imageVector = Icons.AutoMirrored.Filled.ListAlt,
                    contentDescription = null,
                    tint = Cereza
                )
                Text(
                    text = "Borrar sesión",
                    fontWeight = FontWeight.Bold,
                    color = Cereza
                )
            }
        },
        text = {
            Text(
                text = "¿Estás segura que quieres borrar la sesión \'${sesionSeleccionada.nombre}\'?",
                color = RosaRojo
            )
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onSesionEvent(SesionEvent.OnDeleteSesion(sesionUiState = sesionSeleccionada))
                    setMostrarDialogo(false)
                    scope.launch {
                        SnackbarMensaje(
                            mensaje = "Sesión borrada correctamente",
                            snackbarHostState = snackbarHostState
                        )
                    }
                }
            ) {
                Text(text = "Aceptar", color = Cereza)
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    setMostrarDialogo(false)
                }
            ) {
                Text(text = "Cancelar", color = Cereza)
            }
        },
        containerColor = Color.White
    )
}