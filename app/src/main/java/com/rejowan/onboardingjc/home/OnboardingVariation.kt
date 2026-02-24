package com.rejowan.onboardingjc.home

import androidx.compose.ui.graphics.vector.ImageVector
import com.rejowan.onboardingjc.navigation.Routes

data class OnboardingVariation(
    val id: String,
    val name: String,
    val description: String,
    val icon: ImageVector,
    val hasTutorial: Boolean,
    val tutorialUrl: String?,
    val route: Routes,
    val isImplemented: Boolean = true
)
