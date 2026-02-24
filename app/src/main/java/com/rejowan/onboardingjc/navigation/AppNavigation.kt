package com.rejowan.onboardingjc.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.rejowan.onboardingjc.home.HomeScreen
import com.rejowan.onboardingjc.onboarding.classic.ClassicOnboardingScreen
import com.rejowan.onboardingjc.onboarding.fullscreen.FullscreenOnboardingScreen
import com.rejowan.onboardingjc.success.SuccessScreen

@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Routes.Home
    ) {
        composable<Routes.Home> {
            HomeScreen(
                onVariationClick = { route ->
                    navController.navigate(route)
                }
            )
        }

        composable<Routes.ClassicOnboarding> {
            ClassicOnboardingScreen(
                onFinished = {
                    navController.navigate(Routes.Success("Classic Onboarding")) {
                        popUpTo<Routes.ClassicOnboarding> { inclusive = true }
                    }
                }
            )
        }

        composable<Routes.FullscreenOnboarding> {
            FullscreenOnboardingScreen(
                onFinished = {
                    navController.navigate(Routes.Success("Fullscreen Onboarding")) {
                        popUpTo<Routes.FullscreenOnboarding> { inclusive = true }
                    }
                }
            )
        }

        composable<Routes.Success> { backStackEntry ->
            val success: Routes.Success = backStackEntry.toRoute()
            SuccessScreen(
                onboardingName = success.onboardingName,
                onGoHome = {
                    navController.navigate(Routes.Home) {
                        popUpTo<Routes.Home> { inclusive = true }
                    }
                }
            )
        }
    }
}
