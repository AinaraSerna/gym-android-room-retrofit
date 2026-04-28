package com.gym.ui.features.menulateral.sesiones

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gym.data.retrofit.respuestasapi.RespuestaApi
import com.gym.data.retrofit.respuestasapi.SesionApi
import com.gym.data.retrofit.respuestasapi.TotalRegistrosApi
import com.gym.models.servicesimplementation.SesionesServiceImplementation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SesionesApiViewModel @Inject constructor(
    private val sesionesServiceImplementation: SesionesServiceImplementation
) : ViewModel() {
    private val _totalSesiones = MutableStateFlow<TotalRegistrosApi?>(value = null)
    val totalSesiones = _totalSesiones.asStateFlow()
    private val _sesionesApi = MutableStateFlow<List<SesionApi>>(value = emptyList())
    val sesionesApi = _sesionesApi.asStateFlow()

    private val _respuestaApi = MutableStateFlow<RespuestaApi?>(value = null)
    val respuestaApi = _respuestaApi.asStateFlow()

    init {
        countSesiones()
    }

    fun onSesionApiEvent(event: SesionesApiEvent) {
        when (event) {
            is SesionesApiEvent.OnGetSesionesApi -> getSesiones()
            is SesionesApiEvent.OnCountSesionesApi -> countSesiones()
            is SesionesApiEvent.OnPostSesionApi -> postSesion(event.sesionApi)
            is SesionesApiEvent.OnPutSesionesApi -> putSesion(event.sesionApi)
            is SesionesApiEvent.OnDeleteSesionesApi -> deleteSesion(event.sesionApi)
        }
    }

    private fun getSesiones() {
        viewModelScope.launch {
            _sesionesApi.value = sesionesServiceImplementation.get()
        }
    }

    private fun countSesiones() {
        viewModelScope.launch {
            try {
                _totalSesiones.value = sesionesServiceImplementation.countSesiones()
            } catch (_: Exception) {
                _totalSesiones.value = null
            }
        }
    }

    private fun postSesion(sesionApi: SesionApi) {
        viewModelScope.launch {
            _respuestaApi.value = sesionesServiceImplementation.insert(sesionApi)
        }
    }

    private fun putSesion(sesionApi: SesionApi) {
        viewModelScope.launch {
            _respuestaApi.value = sesionesServiceImplementation.update(sesionApi)
        }
    }

    private fun deleteSesion(sesionApi: SesionApi) {
        viewModelScope.launch {
            _respuestaApi.value = sesionesServiceImplementation.delete(sesionApi)
        }
    }
}