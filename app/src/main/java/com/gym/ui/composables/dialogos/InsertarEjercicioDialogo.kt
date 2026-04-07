package com.gym.ui.composables.dialogos

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import com.gym.ui.theme.CerezaDeshabilitado
import com.gym.ui.theme.RojoClaroError
import com.gym.ui.theme.RojoError
import com.gym.ui.theme.RosaPalo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InsertarEjercicioDialogo(
    setMostrarDialogoInsertarEjercicio: (Boolean) -> Unit,
    ejercicios: List<String>,
    onEjercicioEvent: (EjercicioEvent) -> Unit,
    scope: CoroutineScope,
    snackbarHostState: SnackbarHostState,
    sesiones: List<Pair<Int?, String>>
) {
    val (nombreTextField, setNombreTextField) = remember { mutableStateOf(value = "") }
    val (ordenTextField, setOrdenTextField) = remember { mutableStateOf(value = "") }
    val (notasTextField, setNotasTextField) = remember { mutableStateOf(value = "") }
    AlertDialog(
        onDismissRequest = {},
        title = {
            Text(
                text = "Crear ejercicio",
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
                    value = nombreTextField,
                    onValueChange = setNombreTextField,
                    isError = ejercicios.contains(nombreTextField),
                    label = { Text(text = "Nombre") },
                    singleLine = true,
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Cereza,
                        unfocusedIndicatorColor = Cereza,
                        focusedLabelColor = Cereza,
                        focusedContainerColor = RosaPalo.copy(alpha = 185f),
                        unfocusedContainerColor = RosaPalo.copy(alpha = 185f),
                        unfocusedLabelColor = CerezaDeshabilitado,
                        errorContainerColor = RojoClaroError,
                        errorTextColor = RojoError,
                        errorLabelColor = RojoError
                    )
                )
                OutlinedTextField(
                    value = ordenTextField,
                    onValueChange = { setOrdenTextField(it) },
                    label = { Text(text = "Orden") },
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
                        unfocusedLabelColor = CerezaDeshabilitado,
                    )
                )
                OutlinedTextField(
                    modifier = Modifier.height(100.dp),
                    value = notasTextField,
                    onValueChange = setNotasTextField,
                    label = { Text(text = "Notas") },
                    singleLine = false,
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Cereza,
                        unfocusedIndicatorColor = Cereza,
                        focusedLabelColor = Cereza,
                        focusedContainerColor = RosaPalo.copy(alpha = 185f),
                        unfocusedContainerColor = RosaPalo.copy(alpha = 185f),
                        unfocusedLabelColor = CerezaDeshabilitado,
                    )
                )
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onEjercicioEvent(
                        EjercicioEvent.OnInsertEjercicio(
                            EjercicioUiState()
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
                        && !ejercicios.contains(nombreTextField)
                        && notasTextField.isNotEmpty(),
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
        ejercicios = listOf("Pecho plano", "Pecho inclinado"),
        onEjercicioEvent = {},
        scope = rememberCoroutineScope(),
        snackbarHostState = remember { SnackbarHostState() },
        sesiones = listOf(
            Pair(1, "1-A"),
            Pair(2, "2-B")
        )
    )
}