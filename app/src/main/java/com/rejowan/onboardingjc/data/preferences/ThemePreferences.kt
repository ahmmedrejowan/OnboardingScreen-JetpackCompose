package com.rejowan.onboardingjc.data.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

enum class ThemeMode {
    SYSTEM, LIGHT, DARK
}

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "theme_preferences")

class ThemePreferences(private val context: Context) {

    private val _themeMode = MutableStateFlow(loadThemeMode())
    val themeMode: StateFlow<ThemeMode> = _themeMode.asStateFlow()

    private val _dynamicColorEnabled = MutableStateFlow(loadDynamicColor())
    val dynamicColorEnabled: StateFlow<Boolean> = _dynamicColorEnabled.asStateFlow()

    companion object {
        private val KEY_THEME_MODE = stringPreferencesKey("theme_mode")
        private val KEY_DYNAMIC_COLOR = booleanPreferencesKey("dynamic_color")
    }

    private fun loadThemeMode(): ThemeMode {
        return runBlocking {
            val prefs = context.dataStore.data.first()
            val value = prefs[KEY_THEME_MODE] ?: ThemeMode.SYSTEM.name
            try {
                ThemeMode.valueOf(value)
            } catch (e: IllegalArgumentException) {
                ThemeMode.SYSTEM
            }
        }
    }

    private fun loadDynamicColor(): Boolean {
        return runBlocking {
            val prefs = context.dataStore.data.first()
            prefs[KEY_DYNAMIC_COLOR] ?: false
        }
    }

    suspend fun setThemeMode(mode: ThemeMode) {
        context.dataStore.edit { prefs ->
            prefs[KEY_THEME_MODE] = mode.name
        }
        _themeMode.value = mode
    }

    suspend fun setDynamicColorEnabled(enabled: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[KEY_DYNAMIC_COLOR] = enabled
        }
        _dynamicColorEnabled.value = enabled
    }
}
