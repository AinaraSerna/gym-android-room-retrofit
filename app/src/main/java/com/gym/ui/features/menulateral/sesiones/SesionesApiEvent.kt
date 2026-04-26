package com.gym.ui.features.menulateral.sesiones

import com.gym.data.retrofit.respuestasapi.SesionApi

sealed interface SesionesApiEvent {
    data object OnGetSesionesApi : SesionesApiEvent
    data object OnCountSesionesApi : SesionesApiEvent
    data class OnPostSesionApi(val sesionApi: SesionApi) : SesionesApiEvent
    data class OnPutSesionesApi(val sesionApi: SesionApi) : SesionesApiEvent
    data class OnDeleteSesionesApi(val sesionApi: SesionApi) : SesionesApiEvent
}