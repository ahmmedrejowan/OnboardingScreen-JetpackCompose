package com.rejowan.onboardingjc.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.rejowan.onboardingjc.home.HomeScreen
import com.rejowan.onboardingjc.onboarding.classic.ClassicOnboardingScreen
import com.rejowan.onboardingjc.success.SuccessScreen

@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Routes.Home.route
    ) {
        composable(Routes.Home.route) {
            HomeScreen(
                onVariationClick = { route ->
                    navController.navigate(route)
                }
            )
        }

        composable(Routes.ClassicOnboarding.route) {
            ClassicOnboardingScreen(
                onFinished = {
                    navController.navigate(Routes.Success.createRoute("Classic Onboarding")) {
                        popUpTo(Routes.ClassicOnboarding.route) { inclusive = true }
                    }
                }
            )
        }

        composable(
            route = Routes.Success.route,
            arguments = listOf(
                navArgument("onboardingName") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val onboardingName = backStackEntry.arguments?.getString("onboardingName") ?: ""
            SuccessScreen(
                onboardingName = onboardingName,
                onGoHome = {
                    navController.navigate(Routes.Home.route) {
                        popUpTo(Routes.Home.route) { inclusive = true }
                    }
                }
            )
        }
    }
}
