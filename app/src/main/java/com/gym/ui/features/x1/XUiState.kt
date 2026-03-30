package com.gym.ui.features.x1

import com.gym.data.Sesion


data class XUiState(
    val id: Int = 0
)

fun XUiState.toX() = Sesion(
    id = this.id
)

fun Sesion.toXUiState() = XUiState(
    id = this.id
)