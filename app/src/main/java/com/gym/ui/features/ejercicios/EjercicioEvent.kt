package com.gym.ui.features.ejercicios

sealed interface EjercicioEvent {
    data object OnGetEjercicios : EjercicioEvent
    data class OnGetEjercicioById(val id: Int) : EjercicioEvent
    data class OnInsertEjercicio(val ejercicio: EjercicioUiState) : EjercicioEvent
    data class OnUpdateEjercicio(val ejercicio: EjercicioUiState) : EjercicioEvent
    data class OnDeleteEjercicio(val ejercicio: EjercicioUiState) : EjercicioEvent
}