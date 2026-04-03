package com.gym.ui.composables.dialogos

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ListAlt
import androidx.compose.material.icons.filled.Description
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gym.ui.features.sesiones.SesionEvent
import com.gym.ui.features.sesiones.SesionUiState
import com.gym.ui.theme.Cereza
import com.gym.ui.theme.CerezaDeshabilitado
import com.gym.ui.theme.RojoClaroError
import com.gym.ui.theme.RojoError
import com.gym.ui.theme.RosaPaloTransparente

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InsertarSesionDialogo(
    setMostrarDialogoInsertarSesion: (Boolean) -> Unit,
    sesiones: List<SesionUiState>,
    onSesionEvent: (SesionEvent) -> Unit
) {
    val (codigoTextField, setCodigoTextField) = remember { mutableStateOf(value = "") }
    val (descripcionTextField, setDescripcionTextField) = remember { mutableStateOf(value = "") }
    val regexCodigo = remember { Regex(pattern = "^\\d-[A-Z]$") }
    AlertDialog(
        onDismissRequest = {},
        title = {
            Text(
                text = "Crear sesión",
                fontWeight = FontWeight.Bold,
                color = Cereza
            )
        },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                OutlinedTextField(
                    value = codigoTextField,
                    onValueChange = { setCodigoTextField(it) },
                    label = { Text(text = "Código") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ListAlt,
                            contentDescription = null,
                            tint = Cereza
                        )
                    },
                    isError = sesiones.map { it.nombre }.contains(codigoTextField)
                            || !regexCodigo.matches(input = codigoTextField)
                            && codigoTextField.isNotEmpty(),
                    singleLine = true,
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Cereza,
                        unfocusedIndicatorColor = Cereza,
                        focusedLabelColor = Cereza,
                        focusedContainerColor = RosaPaloTransparente,
                        unfocusedContainerColor = RosaPaloTransparente,
                        unfocusedLabelColor = CerezaDeshabilitado,
                        errorContainerColor = RojoClaroError,
                        errorTextColor = RojoError,
                        errorLabelColor = RojoError,
                    )
                )
                OutlinedTextField(
                    modifier = Modifier.height(100.dp),
                    value = descripcionTextField,
                    onValueChange = { setDescripcionTextField(it) },
                    label = { Text(text = "Descripción") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Description,
                            contentDescription = null,
                            tint = Cereza
                        )
                    },
                    singleLine = false,
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Cereza,
                        unfocusedIndicatorColor = Cereza,
                        focusedLabelColor = Cereza,
                        focusedContainerColor = RosaPaloTransparente,
                        unfocusedContainerColor = RosaPaloTransparente,
                        unfocusedLabelColor = CerezaDeshabilitado,
                    )
                )
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onSesionEvent(
                        SesionEvent.OnInsertSesion(
                            SesionUiState(
                                nombre = codigoTextField,
                                descripcion = descripcionTextField
                            )
                        )
                    )
                    setMostrarDialogoInsertarSesion(false)
                },
                enabled = !sesiones.map { it.nombre }.contains(codigoTextField)
                        && regexCodigo.matches(input = codigoTextField)
                        && codigoTextField.isNotEmpty()
                        && descripcionTextField.isNotEmpty(),
                colors = ButtonDefaults.textButtonColors(
                    contentColor = Cereza,
                    disabledContentColor = CerezaDeshabilitado
                )
            ) {
                Text(text = "Guardar")
            }
        },
        dismissButton = {
            TextButton(
                onClick = { setMostrarDialogoInsertarSesion(false) }
            ) {
                Text(text = "Cancelar", color = Cereza)
            }
        },
        tonalElevation = 8.dp,
        containerColor = Color.White
    )
}

@Preview(showBackground = true)
@Composable
fun InsertarSesionDialogoPreview() {
    InsertarSesionDialogo(
        setMostrarDialogoInsertarSesion = {},
        sesiones = listOf(
            SesionUiState(id = 1, nombre = "1-M", descripcion = "")
        ),
        onSesionEvent = {}
    )
}