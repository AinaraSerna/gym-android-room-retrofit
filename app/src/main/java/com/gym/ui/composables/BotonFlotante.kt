package com.gym.ui.composables

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gym.ui.theme.Cereza

@Composable
fun BotonFlotante(onMostrarDialogo: (Boolean) -> Unit) {
    FloatingActionButton(
        modifier = Modifier.size(70.dp),
        onClick = { onMostrarDialogo(true) },
        elevation = FloatingActionButtonDefaults.elevation(
            defaultElevation = 5.dp,
            pressedElevation = 10.dp
        ),
        containerColor = Cereza,
        shape = FloatingActionButtonDefaults.smallShape
    ) {
        Icon(
            modifier = Modifier.size(30.dp),
            imageVector = Icons.Filled.Add,
            contentDescription = "Agregar",
            tint = Color.White
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BotonFlotantePreview() {
    BotonFlotante(onMostrarDialogo = {})
}