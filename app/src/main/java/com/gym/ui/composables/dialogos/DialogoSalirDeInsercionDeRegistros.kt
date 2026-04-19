package com.gym.ui.composables.dialogos

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.gym.ui.features.registros.RegistrosEvent
import com.gym.ui.features.registros.RegistrosViewModel
import com.gym.ui.theme.Cereza
import com.gym.ui.theme.RosaRojo

@Composable
fun DialogoSalirDeInsercionRegistros(
    onMostrarDialogo: (Boolean) -> Unit,
    registrosVM: RegistrosViewModel
) {
    AlertDialog(
        onDismissRequest = {
            registrosVM.onRegistrosEvent(RegistrosEvent.OnSalirDeForm(salir = false))
            onMostrarDialogo(false)
        },
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "Salir de inserción de registros",
                    fontWeight = FontWeight.Bold,
                    color = Cereza,
                    textAlign = TextAlign.Center
                )
            }
        },
        text = {
            Text(
                text = "¿Estás segura que quieres salir de la inserción de registros?",
                color = RosaRojo
            )
        },
        confirmButton = {
            TextButton(
                onClick = {
                    registrosVM.onRegistrosEvent(RegistrosEvent.OnSalirDeForm(salir = true))
                    onMostrarDialogo(false)
                }
            ) {
                Text(text = "Sí", color = Cereza, fontWeight = FontWeight.Bold)
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    registrosVM.onRegistrosEvent(RegistrosEvent.OnSalirDeForm(salir = false))
                    onMostrarDialogo(false)
                }
            ) {
                Text(text = "No", color = Cereza, fontWeight = FontWeight.Bold)
            }
        },
        containerColor = Color.White
    )
}