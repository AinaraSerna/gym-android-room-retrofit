package com.gym.ui.features.historial.formRegistrosDeHistorial

sealed interface RegistrosHistorialEvent {
    data object OnGetEjercicios : RegistrosHistorialEvent
    data object OnGetRegistrosHistorial : RegistrosHistorialEvent
    data class OnUpdateLocalData(val key: String, val peso: String?, val reps: String?) : RegistrosHistorialEvent
    data class OnSaveAll(val onComplete: () -> Unit) : RegistrosHistorialEvent
}