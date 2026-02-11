package com.base.ui.views

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.base.ui.features.x1.XViewModel
import com.base.ui.navigation.NavHostPrincipal
import com.efundae.moviles.nivel4.ud3.reto7.ui.features.settings.SettingsViewModel
import com.pmdm.proyectobase2425.ui.theme.ProyectoBaseTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlin.getValue

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val vm = hiltViewModel<XViewModel>()
            val navController = rememberNavController()
            val settingsViewModel: SettingsViewModel by viewModels()
            val isDarkTheme by settingsViewModel.isDarkTheme.collectAsState()
            ProyectoBaseTheme {
                NavHostPrincipal(
                    xVM = vm,
                    navController = navController,
                    isDarkTheme = isDarkTheme,
                    onThemeChange = { settingsViewModel.setTheme(it) }
                )
            }
        }
    }
}