package com.gym.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ListAlt
import androidx.compose.material.icons.automirrored.outlined.ListAlt
import androidx.compose.material.icons.filled.Checklist
import androidx.compose.material.icons.filled.SportsGymnastics
import androidx.compose.material.icons.outlined.Checklist
import androidx.compose.material.icons.outlined.SportsGymnastics
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.gym.ui.navigation.menulateral.EjerciciosApiRoute
import com.gym.ui.navigation.menulateral.RegistrosApiRoute
import com.gym.ui.navigation.menulateral.SesionesApiRoute
import com.gym.ui.theme.Cereza
import kotlinx.coroutines.launch

@Composable
fun MenuLateral(navController: NavHostController) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val onAbrirMenuLateral: () -> Unit = {
        if (drawerState.isClosed)
            scope.launch {
                drawerState.open()
            }
    }

    val opcionesMenuLateral = listOf(
        OpcionMenuLateral(
            id = 0,
            titulo = "Sesiones",
            iconoPorDefecto = Icons.AutoMirrored.Outlined.ListAlt,
            iconoSeleccionado = Icons.AutoMirrored.Filled.ListAlt,
            accion = {
                navController.navigate(SesionesApiRoute)
                scope.launch {
                    drawerState.close()
                }
            }
        ),
        OpcionMenuLateral(
            id = 1,
            titulo = "Ejercicios",
            iconoPorDefecto = Icons.Outlined.SportsGymnastics,
            iconoSeleccionado = Icons.Filled.SportsGymnastics,
            accion = {
                navController.navigate(EjerciciosApiRoute)
                scope.launch {
                    drawerState.close()
                }
            }
        ),
        OpcionMenuLateral(
            id = 2,
            titulo = "Registros",
            iconoPorDefecto = Icons.Outlined.Checklist,
            iconoSeleccionado = Icons.Filled.Checklist,
            accion = {
                navController.navigate(RegistrosApiRoute)
                scope.launch {
                    drawerState.close()
                }
            }
        )
    )

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                drawerContainerColor = Color.White,
                drawerContentColor = Cereza
            ) {
                Text(
                    text = "Respaldo API Rest",
                    fontWeight = FontWeight.ExtraBold,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(16.dp)
                )
                opcionesMenuLateral.forEachIndexed { index, opcion ->
                    NavigationDrawerItem(
                        label = {
                            Text(
                                text = opcion.titulo,
                                color = Cereza,
                                fontWeight = if (index == opcion.id) FontWeight.Bold else FontWeight.Normal
                            )
                        },
                        selected = index == opcion.id,
                        onClick = opcion.accion,
                        icon = {
                            Icon(
                                imageVector = if (index == opcion.id) opcion.iconoSeleccionado else opcion.iconoPorDefecto,
                                contentDescription = opcion.titulo,
                                tint = Cereza
                            )
                        },
                        colors = NavigationDrawerItemDefaults.colors(
                            selectedContainerColor = Color.White,
                            unselectedContainerColor = Color.White
                        )
                    )
                }
            }
        }
    ) {
        NavHostPrincipal(navController, onAbrirMenuLateral, scope)
    }
}


data class OpcionMenuLateral(
    val id: Int,
    val titulo: String,
    val iconoPorDefecto: ImageVector,
    val iconoSeleccionado: ImageVector,
    val accion: () -> Unit
)