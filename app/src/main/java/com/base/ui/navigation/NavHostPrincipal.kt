package com.base.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.base.ui.features.x1.XViewModel

@Composable
fun NavHostPrincipal(
    xVM: XViewModel,
    navController: NavHostController,
    isDarkTheme: Boolean,
    onThemeChange: (Boolean) -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = ScaffoldPrincipalRoute
    ) {
        scaffoldPrincipalDestination(
            onIrAPantallaB = { id ->
                navController.navigate(route = PantallaBRoute(id))
            },
            xVM = xVM,
            isDarkTheme = isDarkTheme,
            onThemeChange = onThemeChange,
        )
    }
}