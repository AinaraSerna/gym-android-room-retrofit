package com.gym.ui.navigation

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ListAlt
import androidx.compose.material.icons.automirrored.outlined.ListAlt
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.SportsGymnastics
import androidx.compose.material.icons.outlined.History
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.gym.ui.navigation.menulateral.EjerciciosApiRoute
import com.gym.ui.navigation.menulateral.HistorialApiRoute
import com.gym.ui.navigation.menulateral.SesionesApiRoute
import com.gym.ui.theme.Cereza
import com.gym.ui.theme.CerezaOscuro
import com.gym.ui.theme.RosaPalo
import kotlinx.coroutines.launch

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
                destino?.hasRoute(HistorialApiRoute::class) == true -> 2
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
                titulo = "Historial",
                iconoPorDefecto = Icons.Outlined.History,
                iconoSeleccionado = Icons.Filled.History
            )
        )
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            MenuLateralContent(
                iOpcionLateralSeleccionada = iOpcionLateralSeleccionada,
                opcionesMenuLateral = opcionesMenuLateral,
                onOpcionClick = { opcionId ->
                    navegarAOpcionMenuLateral(navController = navController, indice = opcionId)
                    scope.launch { drawerState.close() }
                }
            )
        }
    ) {
        NavHostPrincipal(navController, onAbrirMenuLateral, scope)
    }
}

@Composable
fun MenuLateralContent(
    iOpcionLateralSeleccionada: Int,
    opcionesMenuLateral: List<OpcionMenuLateral>,
    onOpcionClick: (Int) -> Unit
) {
    ModalDrawerSheet(
        drawerContainerColor = RosaPalo.copy(alpha = 0.95f),
        drawerContentColor = Cereza
    ) {
        Text(
            text = "Respaldo API Rest",
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.ExtraBold,
                shadow = Shadow(
                    color = CerezaOscuro,
                    blurRadius = 2f
                )
            ),
            modifier = Modifier.padding(vertical = 16.dp, horizontal = 8.dp),
            color = CerezaOscuro,
            letterSpacing = 1.sp
        )
        opcionesMenuLateral.forEachIndexed { index, opcion ->
            val isSelected = iOpcionLateralSeleccionada == index
            NavigationDrawerItem(
                modifier = Modifier
                    .padding(vertical = 4.dp, horizontal = 2.dp)
                    .border(
                        width = 1.dp,
                        color = if (isSelected) Color.White else Cereza.copy(alpha = 0.3f),
                        shape = MaterialTheme.shapes.medium
                    )
                    .shadow(
                        elevation = if (isSelected) 4.dp else 2.dp,
                        shape = MaterialTheme.shapes.medium,
                        clip = true
                    ),
                label = {
                    Text(
                        text = opcion.titulo,
                        color = if (isSelected) Color.White else Cereza,
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = if (isSelected) FontWeight.ExtraBold else FontWeight.Medium
                        )
                    )
                },
                shape = MaterialTheme.shapes.medium,
                selected = isSelected,
                onClick = { onOpcionClick(opcion.id) },
                icon = {
                    Icon(
                        imageVector = if (isSelected) opcion.iconoSeleccionado else opcion.iconoPorDefecto,
                        contentDescription = opcion.titulo,
                        tint = if (isSelected) Color.White else Cereza
                    )
                },
                colors = NavigationDrawerItemDefaults.colors(
                    selectedContainerColor = Cereza.copy(alpha = 0.9f),
                    unselectedContainerColor = Color.White
                )
            )
        }
    }
}


data class OpcionMenuLateral(
    val id: Int,
    val titulo: String,
    val iconoPorDefecto: ImageVector,
    val iconoSeleccionado: ImageVector
)

@Preview(showBackground = true)
@Composable
fun MenuLateralPreview() {
    MenuLateralContent(
        iOpcionLateralSeleccionada = 0,
        opcionesMenuLateral = listOf(
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
                titulo = "Historial",
                iconoPorDefecto = Icons.Outlined.History,
                iconoSeleccionado = Icons.Filled.History
            )
        ),
        onOpcionClick = {}
    )
}