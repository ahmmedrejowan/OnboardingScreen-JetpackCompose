package com.rejowan.onboardingjc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.rejowan.onboardingjc.onboard.OnboardingScreen
import com.rejowan.onboardingjc.onboard.OnboardingUtils
import com.rejowan.onboardingjc.ui.theme.OnboardingJetpackComposeTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val onboardingUtils by lazy { OnboardingUtils(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        setContent {
            OnboardingJetpackComposeTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    if (onboardingUtils.isOnboardingCompleted()) {
                        ShowHomeScreen()
                    } else {
                        ShowOnboardingScreen()

                    }
                }
            }
        }


    }


    @Composable
    private fun ShowHomeScreen() {

        Column {
            Text(text = "Home Screen", style = MaterialTheme.typography.headlineLarge)
        }


    }

    @Composable
    private fun ShowOnboardingScreen() {
        val scope = rememberCoroutineScope()
        OnboardingScreen {
            onboardingUtils.setOnboardingCompleted()
            scope.launch {
                setContent {
                    ShowHomeScreen()
                }
            }
        }


    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        OnboardingJetpackComposeTheme {
            ShowHomeScreen()
        }
    }

}


