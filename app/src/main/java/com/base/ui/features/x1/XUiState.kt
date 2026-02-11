package com.base.ui.features.x1

import com.base.data.X


data class XUiState(
    val id: Int = 0
)

fun XUiState.toX() = X(
    id = this.id
)

fun X.toXUiState() = XUiState(
    id = this.id
)