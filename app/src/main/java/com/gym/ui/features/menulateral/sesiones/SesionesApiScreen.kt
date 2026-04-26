package com.gym.ui.features.menulateral.sesiones

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.gym.data.retrofit.respuestasapi.TotalRegistrosApi

@Composable
fun SesionesApiScreen(
    totalSesionesApi: TotalRegistrosApi?,
    totalSesionesBD: Int
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        if (totalSesionesApi != null) {
            Text(text = "Nº de ${totalSesionesApi.tabla} en API: ${totalSesionesApi.totalRegistros}")
            Text(text = "Nº de ${totalSesionesApi.tabla} en BD: $totalSesionesBD")
        }
    }
}