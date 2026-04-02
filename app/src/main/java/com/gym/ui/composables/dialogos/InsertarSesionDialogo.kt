package com.gym.ui.composables.dialogos

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.gym.ui.theme.Cereza

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InsertarSesionDialogo(setMostrarDialogoInsertarSesion: (Boolean) -> Unit) {
    AlertDialog(
        onDismissRequest = {},
        title = {
            Text(text = "Insertar Sesión")
        },
        text = { Text(text = "Ingrese los datos de la sesión") },
        confirmButton = {
            TextButton(
                onClick = { setMostrarDialogoInsertarSesion(false) },
            ) {
                Text(text = "Guardar", fontWeight = FontWeight.ExtraBold, color = Cereza)
            }
        },
        dismissButton = {
            TextButton(
                onClick = { setMostrarDialogoInsertarSesion(false) }
            ) {
                Text(text = "Cancelar", fontWeight = FontWeight.ExtraBold, color = Cereza)
            }
        },
        tonalElevation = 8.dp,
        containerColor = Color.White
    )
}