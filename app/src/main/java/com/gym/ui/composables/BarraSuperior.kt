package com.gym.ui.composables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Undo
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.gym.ui.features.sesiones.SesionEvent
import com.gym.ui.navigation.ejercicios.DetallesEjercicioRoute
import com.gym.ui.navigation.historial.FormRegistrosPorFechaRoute
import com.gym.ui.navigation.registros.FormNuevosRegistrosRoute
import com.gym.ui.navigation.sesiones.DetallesSesionRoute
import com.gym.ui.theme.RosaFucsia

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarraSuperior(
    comportamientoAnteScroll: TopAppBarScrollBehavior,
    onAbrirMenuLateral: () -> Unit,
    titulo: String,
    opcionSeleccionada: Boolean,
    setMostrarDialogoEliminacion: (Boolean) -> Unit,
    onSesionEvent: (SesionEvent) -> Unit,
    iOpcionSeleccionada: Int,
    navController: NavHostController
) {
    TopAppBar(
        title = { Text(text = titulo) },
        scrollBehavior = comportamientoAnteScroll,
        navigationIcon = {
            when (iOpcionSeleccionada) {
                in 0..3, in 8..10 -> {
                    IconButton(onClick = onAbrirMenuLateral) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Mostrar menú lateral",
                            tint = Color.White
                        )
                    }
                }
                in 4..7 -> {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver atrás",
                            tint = Color.White
                        )
                    }
                }
            }
        },
        actions = {
            if (opcionSeleccionada) {
                IconButton(
                    onClick = {
                        when (iOpcionSeleccionada) {
                            3 -> onSesionEvent(SesionEvent.OnGetSesionById(null))
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.Undo,
                        contentDescription = "Deshacer",
                        tint = Color.White
                    )
                }
                IconButton(onClick = {
                    when (iOpcionSeleccionada) {
                        0 -> {
                            navController.navigate(FormNuevosRegistrosRoute)
                        }
                        1 -> {
                            navController.navigate(FormRegistrosPorFechaRoute)
                        }
                        2 -> {
                            navController.navigate(DetallesEjercicioRoute)
                        }
                        3 -> {
                            navController.navigate(DetallesSesionRoute)
                        }
                    }
                }) {
                    Icon(
                        imageVector = Icons.Filled.Edit,
                        contentDescription = "Editar",
                        tint = Color.White
                    )
                }
                IconButton(onClick = { setMostrarDialogoEliminacion(true) }) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = "Eliminar",
                        tint = Color.White
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = RosaFucsia,
            titleContentColor = Color.White
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun BarraSuperiorPreview() {
    BarraSuperior(
        comportamientoAnteScroll = TopAppBarDefaults.pinnedScrollBehavior(),
        onAbrirMenuLateral = {},
        titulo = "Registros",
        opcionSeleccionada = false,
        setMostrarDialogoEliminacion = {},
        onSesionEvent = {},
        iOpcionSeleccionada = 0,
        navController = NavHostController(context = LocalContext.current)
    )
}