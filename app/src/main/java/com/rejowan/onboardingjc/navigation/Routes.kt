package com.rejowan.onboardingjc.navigation

sealed class Routes(val route: String) {
    data object Home : Routes("home")
    data object ClassicOnboarding : Routes("classic_onboarding")
    data object Success : Routes("success/{onboardingName}") {
        fun createRoute(onboardingName: String) = "success/$onboardingName"
    }
}
