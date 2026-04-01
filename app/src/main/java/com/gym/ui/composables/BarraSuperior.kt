package com.gym.ui.composables

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.gym.ui.theme.RosaFucsia

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarraSuperior(comportamientoAnteScroll: TopAppBarScrollBehavior) {
    TopAppBar(
        title = { Text("Top App Bar provisional") },
        scrollBehavior = comportamientoAnteScroll,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = RosaFucsia,
            titleContentColor = Color.White
        )
    )
}