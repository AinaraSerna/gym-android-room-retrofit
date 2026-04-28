package com.gym.ui.navigation.menulateral

import androidx.compose.runtime.collectAsState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gym.ui.features.menulateral.sesiones.SesionesApiScreen
import com.gym.ui.features.menulateral.sesiones.SesionesApiViewModel
import com.gym.ui.features.sesiones.SesionesViewModel
import kotlinx.serialization.Serializable

@Serializable
data object SesionesApiRoute

fun NavGraphBuilder.sesionesApiDestination(
    sesionesApiVM: SesionesApiViewModel,
    sesionesVM: SesionesViewModel
) {
    composable<SesionesApiRoute> {
        SesionesApiScreen(
            totalSesionesApi = sesionesApiVM.totalSesiones.collectAsState().value,
            totalSesionesBD = sesionesVM.sesiones.collectAsState().value.size
        )
    }
}