package com.gym.ui.features.sesiones

sealed interface SesionEvent {
    data object OnGetSesiones : SesionEvent
    data class OnGetSesionById(val id: Int?) : SesionEvent
    data class OnInsertSesion(val sesionUiState: SesionUiState) : SesionEvent
    data class OnUpdateSesion(val sesionUiState: SesionUiState) : SesionEvent
    data class OnDeleteSesion(val sesionUiState: SesionUiState) : SesionEvent
}