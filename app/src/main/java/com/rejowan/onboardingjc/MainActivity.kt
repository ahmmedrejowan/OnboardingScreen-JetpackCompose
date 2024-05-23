package com.rejowan.onboardingjc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.rejowan.onboardingjc.ui.theme.OnboardingJetpackComposeTheme

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        setContent {
            OnboardingJetpackComposeTheme {


            }
        }
    }


}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {

}