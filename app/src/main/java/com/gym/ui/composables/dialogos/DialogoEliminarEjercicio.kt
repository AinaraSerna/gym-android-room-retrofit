package com.gym.ui.composables.dialogos

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SportsGymnastics
import androidx.compose.material3.AlertDialog
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
import com.gym.ui.features.ejercicios.EjercicioEvent
import com.gym.ui.features.ejercicios.EjercicioUiState
import com.gym.ui.theme.Cereza
import com.gym.ui.theme.RosaRojo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun DialogoEliminarEjercicio(
    setMostrarDialogo: (Boolean) -> Unit,
    snackbarHostState: SnackbarHostState,
    scope: CoroutineScope,
    onEjercicioEvent: (EjercicioEvent) -> Unit,
    ejercicioSeleccionado: EjercicioUiState
){
    AlertDialog(
        onDismissRequest = {},
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    modifier = Modifier.padding(end = 6.dp),
                    imageVector = Icons.Filled.SportsGymnastics,
                    contentDescription = null,
                    tint = Cereza
                )
                Text(
                    text = "Borrar ejercicio",
                    fontWeight = FontWeight.Bold,
                    color = Cereza
                )
            }
        },
        text = {
            Text(
                text = "¿Estás segura que quieres borrar el ejercicio \'${ejercicioSeleccionado.nombre}\'?",
                color = RosaRojo
            )
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onEjercicioEvent(EjercicioEvent.OnDeleteEjercicio(ejercicio = ejercicioSeleccionado))
                    setMostrarDialogo(false)
                    scope.launch {
                        SnackbarMensaje(
                            mensaje = "Ejercicio borrado correctamente",
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
                    onEjercicioEvent(EjercicioEvent.OnGetEjercicioById(null))
                    setMostrarDialogo(false)
                }
            ) {
                Text(text = "Cancelar", color = Cereza)
            }
        },
        containerColor = Color.White
    )
}