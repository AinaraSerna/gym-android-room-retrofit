package com.gym.ui.navigation.menulateral

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gym.ui.features.menulateral.sesiones.SesionesApiScreen
import kotlinx.serialization.Serializable

@Serializable
data object SesionesApiRoute

fun NavGraphBuilder.sesionesApiDestination() {
    composable<SesionesApiRoute>{
        SesionesApiScreen()
    }
}