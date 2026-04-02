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
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.gym.ui.navigation.menulateral.EjerciciosApiRoute
import com.gym.ui.navigation.menulateral.RegistrosApiRoute
import com.gym.ui.navigation.menulateral.SesionesApiRoute
import com.gym.ui.theme.Cereza
import com.gym.ui.theme.RosaPalo
import kotlinx.coroutines.launch
import kotlin.reflect.KClass

@Composable
fun MenuLateral(navController: NavHostController) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val iOpcionLateralSeleccionada by remember {
        derivedStateOf {
            val destino = navBackStackEntry?.destination
            when {
                destino?.hasRoute(SesionesApiRoute::class) == true -> 0
                destino?.hasRoute(EjerciciosApiRoute::class) == true -> 1
                destino?.hasRoute(RegistrosApiRoute::class) == true -> 2
                else -> -1
            }
        }
    }

    val onAbrirMenuLateral: () -> Unit = {
        if (drawerState.isClosed)
            scope.launch {
                drawerState.open()
            }
    }

    val opcionesMenuLateral = remember {
        listOf(
            OpcionMenuLateral(
                id = 0,
                titulo = "Sesiones",
                iconoPorDefecto = Icons.AutoMirrored.Outlined.ListAlt,
                iconoSeleccionado = Icons.AutoMirrored.Filled.ListAlt
            ),
            OpcionMenuLateral(
                id = 1,
                titulo = "Ejercicios",
                iconoPorDefecto = Icons.Outlined.SportsGymnastics,
                iconoSeleccionado = Icons.Filled.SportsGymnastics
            ),
            OpcionMenuLateral(
                id = 2,
                titulo = "Registros",
                iconoPorDefecto = Icons.Outlined.Checklist,
                iconoSeleccionado = Icons.Filled.Checklist
            )
        )
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                drawerContainerColor = RosaPalo,
                drawerContentColor = Cereza
            ) {
                Text(
                    text = "Respaldo API Rest",
                    fontWeight = FontWeight.ExtraBold,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(all = 16.dp)
                )
                opcionesMenuLateral.forEachIndexed { index, opcion ->
                    val isSelected = iOpcionLateralSeleccionada == index
                    NavigationDrawerItem(
                        label = {
                            Text(
                                text = opcion.titulo,
                                color = Cereza,
                                fontWeight = if (isSelected) FontWeight.ExtraBold else FontWeight.Normal
                            )
                        },
                        selected = isSelected,
                        onClick = {
                            navegarAOpcionMenuLateral(navController = navController, indice = opcion.id)
                            scope.launch { drawerState.close() }
                        },
                        icon = {
                            Icon(
                                imageVector = if (isSelected) opcion.iconoSeleccionado else opcion.iconoPorDefecto,
                                contentDescription = opcion.titulo,
                                tint = Cereza
                            )
                        },
                        colors = NavigationDrawerItemDefaults.colors(
                            selectedContainerColor = Color(0xFFF5F5F5),
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
    val iconoSeleccionado: ImageVector
)