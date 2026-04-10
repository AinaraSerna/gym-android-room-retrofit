package com.gym.ui.composables.dialogos

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ListAlt
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Numbers
import androidx.compose.material.icons.filled.SportsGymnastics
import androidx.compose.material.icons.filled.Timelapse
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gym.ui.composables.SnackbarMensaje
import com.gym.ui.features.ejercicios.EjercicioEvent
import com.gym.ui.features.ejercicios.EjercicioUiState
import com.gym.ui.theme.Cereza
import com.gym.ui.theme.RojoClaroError
import com.gym.ui.theme.RojoError
import com.gym.ui.theme.RosaPalo
import com.gym.ui.theme.RosaRojo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InsertarEjercicioDialogo(
    setMostrarDialogoInsertarEjercicio: (Boolean) -> Unit,
    ejercicios: List<Pair<String, Int?>>,
    onEjercicioEvent: (EjercicioEvent) -> Unit,
    scope: CoroutineScope,
    snackbarHostState: SnackbarHostState,
    sesiones: List<Pair<Int?, String>>
) {
    val (nombreTextField, setNombreTextField) = remember { mutableStateOf(value = "") }
    val (ordenTextField, setOrdenTextField) = remember { mutableStateOf(value = "") }
    val (serieTextField, setSerieTextField) = remember { mutableStateOf(value = "3") }
    val (notasTextField, setNotasTextField) = remember { mutableStateOf(value = "") }
    var expanded by remember { mutableStateOf(value = false) }
    var sesionSeleccionada by remember { mutableStateOf(value = sesiones.firstOrNull()) }

    AlertDialog(
        onDismissRequest = {},
        title = {
            Text(
                text = "Añadir ejercicio",
                fontWeight = FontWeight.Bold,
                color = Cereza
            )
        },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = nombreTextField,
                    onValueChange = setNombreTextField,
                    isError = ejercicios.map { it.first }.contains(nombreTextField),
                    label = { Text(text = "Nombre") },
                    placeholder = {
                        Text(text = "Nombre", color = Cereza.copy(alpha = 0.5f))
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.SportsGymnastics,
                            contentDescription = null,
                            tint = Cereza
                        )
                    },
                    singleLine = true,
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Cereza,
                        unfocusedIndicatorColor = Cereza,
                        focusedLabelColor = Cereza,
                        focusedContainerColor = RosaPalo.copy(alpha = 185f),
                        unfocusedContainerColor = RosaPalo.copy(alpha = 185f),
                        unfocusedLabelColor = Cereza,
                        errorContainerColor = RojoClaroError,
                        errorTextColor = RojoError,
                        errorLabelColor = RojoError
                    )
                )

                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = { expanded = !expanded },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .menuAnchor(type = MenuAnchorType.PrimaryNotEditable, enabled = true),
                        readOnly = true,
                        value = sesionSeleccionada?.second ?: "",
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
                        sesiones.forEach { sesion ->
                            DropdownMenuItem(
                                modifier = Modifier
                                    .background(
                                        color = if (sesion == sesionSeleccionada) RosaRojo.copy(
                                            alpha = 0.3f
                                        ) else RosaPalo.copy(alpha = 0.3f)
                                    ),
                                text = {
                                    Text(
                                        text = sesion.second,
                                        fontWeight = if (sesion == sesionSeleccionada) FontWeight.ExtraBold else FontWeight.Normal
                                    )
                                },
                                onClick = {
                                    sesionSeleccionada = sesion
                                    setOrdenTextField(
                                        (ejercicios.filter { it.second == sesionSeleccionada!!.first }.size + 1).toString()
                                    )
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

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
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

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = serieTextField,
                    onValueChange = setSerieTextField,
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
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onEjercicioEvent(
                        EjercicioEvent.OnInsertEjercicio(
                            EjercicioUiState(
                                id = null,
                                nombre = nombreTextField,
                                orden = ordenTextField.toIntOrNull() ?: 0,
                                serie = serieTextField.toIntOrNull() ?: 0,
                                codSesion = sesionSeleccionada?.first,
                                notas = notasTextField
                            )
                        )
                    )
                    setMostrarDialogoInsertarEjercicio(false)
                    scope.launch {
                        SnackbarMensaje(
                            mensaje = "Ejercicio creado correctamente",
                            snackbarHostState = snackbarHostState
                        )
                    }
                },
                enabled = nombreTextField.isNotEmpty()
                        && ordenTextField.isNotEmpty()
                        && !ejercicios.map { it.first }.contains(nombreTextField)
                        && sesionSeleccionada?.first != null
                        && notasTextField.isNotEmpty(),
                colors = ButtonDefaults.textButtonColors(
                    contentColor = Cereza,
                    disabledContentColor = Cereza
                )
            ) {
                Text(text = "Guardar")
            }
        },
        dismissButton = {
            TextButton(
                onClick = { setMostrarDialogoInsertarEjercicio(false) },
                colors = ButtonDefaults.textButtonColors(
                    contentColor = Cereza,
                )
            ) {
                Text(text = "Cancelar")
            }
        },
        tonalElevation = 8.dp,
        containerColor = Color.White
    )
}

@Preview(showBackground = true)
@Composable
fun InsertarEjercicioDialogoPreview() {
    InsertarEjercicioDialogo(
        setMostrarDialogoInsertarEjercicio = {},
        ejercicios = listOf(Pair("Pecho plano", 1), Pair("Pecho inclinado", 2)),
        onEjercicioEvent = {},
        scope = rememberCoroutineScope(),
        snackbarHostState = remember { SnackbarHostState() },
        sesiones = listOf(
            Pair(1, "1-A"),
            Pair(2, "2-B")
        )
    )
}