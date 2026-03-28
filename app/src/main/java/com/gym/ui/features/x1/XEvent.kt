package com.gym.ui.features.x1

sealed interface XEvent {
    data class onGetX(val id: Int) : XEvent
    data class onInsertX(val xUiState: XUiState) : XEvent
    data class onUpdate(val xUiState: XUiState) : XEvent
    data class onDeleteX(val xUiState: XUiState) : XEvent
}