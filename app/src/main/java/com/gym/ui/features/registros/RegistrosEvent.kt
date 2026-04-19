package com.gym.ui.features.registros

sealed interface RegistrosEvent {
    data object OnGetSesiones : RegistrosEvent
    data class OnGetSesionById(val id : Int?) : RegistrosEvent
    data class OnInsertRegistro(val registroUiState: RegistroUiState) : RegistrosEvent
    data class OnSalirDeForm(val salir: Boolean) : RegistrosEvent
}