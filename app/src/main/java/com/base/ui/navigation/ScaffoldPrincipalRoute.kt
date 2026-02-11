package com.base.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.base.ui.features.x1.XViewModel
import com.base.ui.features.x1.ScaffoldPrincipalScreen
import kotlinx.serialization.Serializable

@Serializable
data object ScaffoldPrincipalRoute

fun NavGraphBuilder.scaffoldPrincipalDestination(
    onIrAPantallaB: (Int) -> Unit,
    xVM:  XViewModel,
    isDarkTheme: Boolean,
    onThemeChange: (Boolean) -> Unit
) {
    composable<ScaffoldPrincipalRoute> {
        ScaffoldPrincipalScreen(
            onIrPantallaB = onIrAPantallaB,
            xVm = xVM,
            isDarkTheme = isDarkTheme,
            onThemeChange = onThemeChange
        )
    }
}