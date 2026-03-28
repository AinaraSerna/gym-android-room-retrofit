package com.gym.ui.features.x1

import android.annotation.SuppressLint
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.gym.models.XRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@SuppressLint("MutableCollectionMutableState")
@HiltViewModel
class XViewModel @Inject constructor(
    private val repository: XRepository
) : ViewModel() {

    var xState by mutableStateOf(cargarX())
    var tareaSeleccionada: XUiState? by mutableStateOf(XUiState())
    private fun cargarX() = repository.get().map { it.toXUiState() }.toMutableList()


    fun onXEvent(xEvent: XEvent) {
        when (xEvent) {
            is XEvent.onGetX -> {
                xSeleccionado = repository.get(xEvent.id)?.toXUiState()
            }

            is XEvent.onInsertX -> {
                repository.insert(xEvent.xUiState.toX())
            }

            is XEvent.onUpdate -> {
                repository.update(xEvent.xUiState.toX())
            }

            is XEvent.onDeleteX -> {
                repository.delete(xEvent.xUiState.toX())
            }
        }
    }
}