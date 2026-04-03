package com.gym.ui.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ListAlt
import androidx.compose.material.icons.automirrored.outlined.ListAlt
import androidx.compose.material.icons.filled.Checklist
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.SportsGymnastics
import androidx.compose.material.icons.outlined.Checklist
import androidx.compose.material.icons.outlined.History
import androidx.compose.material.icons.outlined.SportsGymnastics
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.gym.ui.theme.Cereza
import com.gym.ui.theme.RosaPalo

@Composable
fun BarraInferior(
    iOpcionSeleccionada: Int,
    onNavegarAPantalla: (Int) -> Unit
) {
    val opcionesBarraInferior = remember {
        listOf(
            OpcionBarraInferior(
                id = 0,
                titulo = "Registros",
                iconoPorDefecto = Icons.Outlined.Checklist,
                iconoSeleccionado = Icons.Filled.Checklist
            ),
            OpcionBarraInferior(
                id = 1,
                titulo = "Historial",
                iconoPorDefecto = Icons.Outlined.History,
                iconoSeleccionado = Icons.Filled.History
            ),
            OpcionBarraInferior(
                id = 2,
                titulo = "Ejercicios",
                iconoPorDefecto = Icons.Outlined.SportsGymnastics,
                iconoSeleccionado = Icons.Filled.SportsGymnastics
            ),
            OpcionBarraInferior(
                id = 3,
                titulo = "Sesiones",
                iconoPorDefecto = Icons.AutoMirrored.Outlined.ListAlt,
                iconoSeleccionado = Icons.AutoMirrored.Filled.ListAlt
            )
        )
    }
    NavigationBar(
        containerColor = RosaPalo
    ) {
        opcionesBarraInferior.forEachIndexed { index, opcion ->
            NavigationBarItem(
                selected = index == iOpcionSeleccionada,
                onClick = { onNavegarAPantalla(index) },
                label = {
                    Text(
                        text = opcion.titulo,
                        color = Cereza,
                        fontWeight = if (iOpcionSeleccionada == index) FontWeight.ExtraBold else FontWeight.Normal,
                        style = if (iOpcionSeleccionada == index) TextStyle(
                            shadow = Shadow(
                                color = Color.Black.copy(alpha = 0.3f),
                                offset = Offset(x = 2f, y = 2f),
                                blurRadius = 2f
                            )
                        ) else TextStyle.Default
                    )

                },
                icon = {
                    Icon(
                        imageVector = if (iOpcionSeleccionada == index) opcion.iconoSeleccionado else opcion.iconoPorDefecto,
                        contentDescription = opcion.titulo,
                        tint = if (iOpcionSeleccionada == index) Color.White else Cereza
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Cereza
                )
            )
        }
    }
}

data class OpcionBarraInferior(
    val id: Int,
    val titulo: String,
    val iconoPorDefecto: ImageVector,
    val iconoSeleccionado: ImageVector
)
