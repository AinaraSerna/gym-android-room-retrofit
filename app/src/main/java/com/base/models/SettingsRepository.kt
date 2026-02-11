package com.base.models

import android.content.SharedPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton
import androidx.core.content.edit

@Singleton
class SettingsRepository @Inject constructor(private val sharedPreferences: SharedPreferences) {

    private val _isDarkTheme = MutableStateFlow(isDarkTheme())
    val isDarkThemeFlow: Flow<Boolean> = _isDarkTheme.asStateFlow()

    private fun isDarkTheme(): Boolean {
        return sharedPreferences.getBoolean(KEY_THEME, false) // Default to light theme
    }

    fun setTheme(isDark: Boolean) {
        sharedPreferences.edit { putBoolean(KEY_THEME, isDark) }
        _isDarkTheme.value = isDark
    }

    companion object {
        const val KEY_THEME = "theme_preference"
    }
}
