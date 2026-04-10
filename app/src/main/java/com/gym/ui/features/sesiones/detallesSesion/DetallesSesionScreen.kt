package com.gym.ui.features.sesiones.detallesSesion

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ListAlt
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gym.ui.composables.SnackbarMensaje
import com.gym.ui.features.sesiones.SesionEvent
import com.gym.ui.features.sesiones.SesionUiState
import com.gym.ui.theme.Cereza
import com.gym.ui.theme.RojoClaroError
import com.gym.ui.theme.RojoError
import com.gym.ui.theme.RosaPalo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun DetallesSesionScreen(
    sesionSeleccionada: SesionUiState,
    onSesionEvent: (SesionEvent) -> Unit,
    sesiones: List<SesionUiState>,
    onIrAtras: () -> Unit,
    scope: CoroutineScope,
    snackbarHostState: SnackbarHostState
) {
    val (nombreTextField, setNombreTextField) = remember { mutableStateOf(value = sesionSeleccionada.nombre) }
    val (descripcionTextField, setDescripcionTextField) = remember { mutableStateOf(value = sesionSeleccionada.descripcion) }
    val regexCodigo = remember { Regex(pattern = "\\d-[A-Z]") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp)
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            value = sesionSeleccionada.id.toString(),
            onValueChange = {},
            label = { Text(text = "ID") },
            readOnly = true,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Info,
                    contentDescription = null,
                    tint = Cereza
                )
            },
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Cereza,
                unfocusedIndicatorColor = Cereza,
                unfocusedContainerColor = RosaPalo.copy(alpha = 185f),
                focusedContainerColor = RosaPalo.copy(alpha = 185f),
                focusedLabelColor = Cereza,
                unfocusedLabelColor = Cereza,
                cursorColor = Cereza
            )
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            value = nombreTextField,
            onValueChange = setNombreTextField,
            label = { Text(text = "Nombre") },
            singleLine = true,
            leadingIcon = {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ListAlt,
                    contentDescription = null,
                    tint = Cereza
                )
            },
            isError = !regexCodigo.matches(input = nombreTextField)
                    || sesiones.any { it.nombre == nombreTextField && it.id != sesionSeleccionada.id },
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Cereza,
                unfocusedIndicatorColor = Cereza,
                unfocusedContainerColor = RosaPalo.copy(alpha = 185f),
                focusedContainerColor = RosaPalo.copy(alpha = 185f),
                focusedLabelColor = Cereza,
                unfocusedLabelColor = Cereza,
                cursorColor = Cereza,
                errorLabelColor = RojoError,
                errorIndicatorColor = RojoError,
                errorCursorColor = RojoError,
                errorContainerColor = RojoClaroError
            )
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .padding(bottom = 16.dp),
            value = descripcionTextField,
            onValueChange = setDescripcionTextField,
            label = { Text(text = "Descripción") },
            singleLine = false,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Description,
                    contentDescription = null,
                    tint = Cereza
                )
            },
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Cereza,
                unfocusedIndicatorColor = Cereza,
                unfocusedContainerColor = RosaPalo.copy(alpha = 185f),
                focusedContainerColor = RosaPalo.copy(alpha = 185f),
                focusedLabelColor = Cereza,
                unfocusedLabelColor = Cereza,
                cursorColor = Cereza
            )
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                modifier = Modifier.size(width = 120.dp, height = 60.dp),
                onClick = {
                    onSesionEvent(
                        SesionEvent.OnUpdateSesion(
                            SesionUiState(
                                id = sesionSeleccionada.id,
                                nombre = nombreTextField,
                                descripcion = descripcionTextField
                            )
                        )
                    )
                    onIrAtras()
                    scope.launch {
                        SnackbarMensaje(
                            mensaje = "Sesión guardada correctamente",
                            snackbarHostState = snackbarHostState
                        )
                    }
                },
                enabled = nombreTextField.isNotEmpty() && descripcionTextField.isNotEmpty()
                        && regexCodigo.matches(nombreTextField)
                        && sesiones.none { it.nombre == nombreTextField && it.id != sesionSeleccionada.id }
                        && (nombreTextField != sesionSeleccionada.nombre
                        || descripcionTextField != sesionSeleccionada.descripcion),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Cereza
                ),
                shape = MaterialTheme.shapes.small,
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 8.dp
                )
            ) {
                Text(
                    text = "Guardar",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DestallesSesionScreenPreview() {
    DetallesSesionScreen(
        sesionSeleccionada = SesionUiState(
            id = 1,
            nombre = "Nombre de la sesión",
            descripcion = "Descripción de la sesión"
        ),
        onSesionEvent = {},
        sesiones = emptyList(),
        onIrAtras = {},
        scope = rememberCoroutineScope(),
        snackbarHostState = SnackbarHostState()
    )
}