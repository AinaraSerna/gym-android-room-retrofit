package com.gym.ui.features.ejercicios.detallesEjercicio

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ListAlt
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Numbers
import androidx.compose.material.icons.filled.Timelapse
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.gym.ui.composables.snackbarMensaje
import com.gym.ui.features.ejercicios.EjercicioEvent
import com.gym.ui.features.ejercicios.EjercicioUiState
import com.gym.ui.features.sesiones.SesionUiState
import com.gym.ui.theme.Cereza
import com.gym.ui.theme.CerezaDeshabilitado
import com.gym.ui.theme.RojoClaroError
import com.gym.ui.theme.RojoError
import com.gym.ui.theme.RosaPalo
import com.gym.ui.theme.RosaRojo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetallesEjercicioScreen(
    onIrAtras: () -> Unit,
    scope: CoroutineScope,
    snackbarHostState: SnackbarHostState,
    onEjercicioEvent: (EjercicioEvent) -> Unit,
    listaEjercicios: List<EjercicioUiState>,
    ejercicioSeleccionado: EjercicioUiState,
    listaSesiones: List<SesionUiState>
) {
    val (nombreTextField, setNombreTextField) = remember {
        mutableStateOf(value = ejercicioSeleccionado.nombre)
    }
    var expanded by remember { mutableStateOf(value = false) }
    val (codSesionSeleccionado, setCodSesionSeleccionado) = remember {
        mutableStateOf(value = ejercicioSeleccionado.codSesion)
    }
    val (seriesTextField, setSeriesTextField) = remember {
        mutableStateOf(value = ejercicioSeleccionado.serie.toString())
    }
    val (ordenTextField, setOrdenTextField) = remember {
        mutableStateOf(value = ejercicioSeleccionado.orden.toString())
    }
    val (notasTextField, setNotasTextField) = remember {
        mutableStateOf(value = ejercicioSeleccionado.notas)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp)
    ) {
        // ID
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            value = ejercicioSeleccionado.id.toString(),
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

        // Sesión
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor(type = MenuAnchorType.PrimaryNotEditable, enabled = true),
                readOnly = true,
                value = listaSesiones.find { it.id == codSesionSeleccionado }?.nombre ?: "",
                onValueChange = {},
                label = { Text("Sesión") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ListAlt,
                        contentDescription = null,
                        tint = Cereza
                    )
                },
                singleLine = true,
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = expanded
                    )
                },
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Cereza,
                    unfocusedIndicatorColor = Cereza,
                    focusedLabelColor = Cereza,
                    focusedContainerColor = RosaPalo.copy(alpha = 185f),
                    unfocusedContainerColor = RosaPalo.copy(alpha = 185f),
                    unfocusedLabelColor = Cereza,
                )
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                containerColor = Color.White
            ) {
                listaSesiones.forEach { sesion ->
                    DropdownMenuItem(
                        modifier = Modifier
                            .background(
                                color = if (sesion.id == codSesionSeleccionado) RosaRojo.copy(
                                    alpha = 0.3f
                                ) else RosaPalo.copy(alpha = 0.3f)
                            ),
                        text = {
                            Text(
                                text = sesion.nombre,
                                fontWeight = if (sesion.id == codSesionSeleccionado) FontWeight.ExtraBold else FontWeight.Normal
                            )
                        },
                        onClick = {
                            setCodSesionSeleccionado(sesion.id)
                            expanded = false
                        },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                        colors = MenuDefaults.itemColors(
                            textColor = Cereza
                        )
                    )
                }
            }
        }

        // Orden
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            value = ordenTextField,
            onValueChange = setOrdenTextField,
            label = { Text(text = "Orden") },
            placeholder = {
                Text(text = "Orden", color = Cereza.copy(alpha = 0.5f))
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Numbers,
                    contentDescription = null,
                    tint = Cereza
                )
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            ),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Cereza,
                unfocusedIndicatorColor = Cereza,
                focusedLabelColor = Cereza,
                focusedContainerColor = RosaPalo.copy(alpha = 185f),
                unfocusedContainerColor = RosaPalo.copy(alpha = 185f),
                unfocusedLabelColor = Cereza,
            )
        )

        // Nombre
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
            isError = listaEjercicios.any { it.nombre == nombreTextField && it.id != ejercicioSeleccionado.id },
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

        // Series
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            value = seriesTextField,
            onValueChange = setSeriesTextField,
            label = { Text(text = "Nº de series") },
            placeholder = {
                Text(text = "Nº de series", color = Cereza.copy(alpha = 0.5f))
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Timelapse,
                    contentDescription = null,
                    tint = Cereza
                )
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            ),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Cereza,
                unfocusedIndicatorColor = Cereza,
                focusedLabelColor = Cereza,
                focusedContainerColor = RosaPalo.copy(alpha = 185f),
                unfocusedContainerColor = RosaPalo.copy(alpha = 185f),
                unfocusedLabelColor = Cereza,
            )
        )

        // Notas
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            value = notasTextField,
            onValueChange = setNotasTextField,
            label = { Text(text = "Notas") },
            placeholder = {
                Text(text = "Notas", color = Cereza.copy(alpha = 0.5f))
            },
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
                focusedContainerColor = RosaPalo.copy(alpha = 185f),
                unfocusedContainerColor = RosaPalo.copy(alpha = 185f),
                unfocusedLabelColor = Cereza,
            )
        )

        // Botón
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                modifier = Modifier
                    .size(width = 120.dp, height = 60.dp)
                    .padding(top = 10.dp),
                onClick = {
                    onEjercicioEvent(
                        EjercicioEvent.OnUpdateEjercicio(
                            EjercicioUiState(
                                id = ejercicioSeleccionado.id,
                                nombre = nombreTextField.trimEnd(),
                                orden = ordenTextField.toIntOrNull() ?: 0,
                                serie = seriesTextField.toIntOrNull() ?: 0,
                                codSesion = ejercicioSeleccionado.codSesion,
                                notas = notasTextField.trimEnd()
                            )
                        )
                    )
                    onEjercicioEvent(EjercicioEvent.OnGetEjercicioById(null))
                    onIrAtras()
                    scope.launch {
                        snackbarMensaje(
                            mensaje = "Ejercicio guardado correctamente",
                            snackbarHostState = snackbarHostState
                        )
                    }
                },
                enabled = nombreTextField.isNotEmpty() && ordenTextField.isNotEmpty()
                        && notasTextField.isNotEmpty() && seriesTextField.isNotEmpty()
                        && seriesTextField.isNotEmpty()
                        && listaEjercicios.none { it.codSesion == ejercicioSeleccionado.codSesion }
                        || (nombreTextField != ejercicioSeleccionado.nombre)
                        || (ordenTextField != ejercicioSeleccionado.orden.toString())
                        || (seriesTextField != ejercicioSeleccionado.serie.toString())
                        || (notasTextField != ejercicioSeleccionado.notas)
                        || (codSesionSeleccionado != ejercicioSeleccionado.codSesion),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Cereza,
                    contentColor = Color.White,
                    disabledContainerColor = CerezaDeshabilitado,
                    disabledContentColor = Color.White.copy(alpha = 0.6f)
                ),
                shape = MaterialTheme.shapes.medium,
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