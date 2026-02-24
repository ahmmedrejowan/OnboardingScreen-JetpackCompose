package com.rejowan.onboardingjc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.remember
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.rejowan.onboardingjc.data.preferences.ThemePreferences
import com.rejowan.onboardingjc.navigation.AppNavigation
import com.rejowan.onboardingjc.ui.theme.OnboardingJetpackComposeTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()

        val themePreferences = ThemePreferences(applicationContext)

        setContent {
            OnboardingJetpackComposeTheme(themePreferences = themePreferences) {
                Surface(color = MaterialTheme.colorScheme.background) {
                    AppNavigation(themePreferences = themePreferences)
                }
            }
        }
    }
}
