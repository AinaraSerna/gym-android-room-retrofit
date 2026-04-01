package com.gym.ui.composables

import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.tooling.preview.Preview
import com.gym.ui.theme.RosaFucsia
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarraSuperior(
    comportamientoAnteScroll: TopAppBarScrollBehavior,
    onAbrirMenuLateral: () -> Unit
) {
    TopAppBar(
        title = { Text("Top App Bar provisional") },
        scrollBehavior = comportamientoAnteScroll,
        navigationIcon = {
            IconButton(onClick = onAbrirMenuLateral) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Mostrar menú lateral",
                    tint = Color.White
                )
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
        onAbrirMenuLateral = {}
    )
}