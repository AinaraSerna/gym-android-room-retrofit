package com.gym.ui.navigation.menulateral

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gym.ui.features.menulateral.registros.RegistrosApiScreen
import kotlinx.serialization.Serializable

@Serializable
data object RegistrosApiRoute

fun NavGraphBuilder.registrosApiDestination() {
    composable<RegistrosApiRoute>{
        RegistrosApiScreen()
    }
}