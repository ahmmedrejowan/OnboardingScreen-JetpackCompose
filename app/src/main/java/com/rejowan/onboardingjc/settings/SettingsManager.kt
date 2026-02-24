package com.rejowan.onboardingjc.settings

import android.content.Context

class SettingsManager(private val context: Context) {

    private val prefs by lazy {
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun isOnboardingCompleted(variationId: String): Boolean {
        return prefs.getBoolean(getKey(variationId), false)
    }

    fun setOnboardingCompleted(variationId: String) {
        prefs.edit()
            .putBoolean(getKey(variationId), true)
            .apply()
    }

    fun resetOnboardingState(variationId: String) {
        prefs.edit()
            .putBoolean(getKey(variationId), false)
            .apply()
    }

    fun resetAllOnboardingStates() {
        prefs.edit()
            .clear()
            .apply()
    }

    private fun getKey(variationId: String): String {
        return "$KEY_PREFIX$variationId"
    }

    companion object {
        private const val PREFS_NAME = "onboarding_showcase_prefs"
        private const val KEY_PREFIX = "onboarding_completed_"
    }
}
