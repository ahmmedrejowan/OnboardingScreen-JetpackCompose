package com.rejowan.onboardingjc.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.rejowan.onboardingjc.home.HomeScreen
import com.rejowan.onboardingjc.onboarding.animated.AnimatedOnboardingScreen
import com.rejowan.onboardingjc.onboarding.bottomsheet.BottomSheetOnboardingScreen
import com.rejowan.onboardingjc.onboarding.cardstack.CardStackOnboardingScreen
import com.rejowan.onboardingjc.onboarding.classic.ClassicOnboardingScreen
import com.rejowan.onboardingjc.onboarding.floating.FloatingOnboardingScreen
import com.rejowan.onboardingjc.onboarding.fullscreen.FullscreenOnboardingScreen
import com.rejowan.onboardingjc.onboarding.gradient.GradientOnboardingScreen
import com.rejowan.onboardingjc.onboarding.minimalist.MinimalistOnboardingScreen
import com.rejowan.onboardingjc.onboarding.morphing.MorphingOnboardingScreen
import com.rejowan.onboardingjc.onboarding.parallax.ParallaxOnboardingScreen
import com.rejowan.onboardingjc.onboarding.splitscreen.SplitScreenOnboardingScreen
import com.rejowan.onboardingjc.onboarding.stepper.StepperOnboardingScreen
import com.rejowan.onboardingjc.onboarding.timeline.TimelineOnboardingScreen
import com.rejowan.onboardingjc.onboarding.verticalpager.VerticalPagerOnboardingScreen
import com.rejowan.onboardingjc.onboarding.videobackground.VideoBackgroundOnboardingScreen
import com.rejowan.onboardingjc.onboarding.zoomtransition.ZoomTransitionOnboardingScreen
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

        composable<Routes.MinimalistOnboarding> {
            MinimalistOnboardingScreen(
                onFinished = {
                    navController.navigate(Routes.Success("Minimalist Onboarding")) {
                        popUpTo<Routes.MinimalistOnboarding> { inclusive = true }
                    }
                }
            )
        }

        composable<Routes.AnimatedOnboarding> {
            AnimatedOnboardingScreen(
                onFinished = {
                    navController.navigate(Routes.Success("Animated Onboarding")) {
                        popUpTo<Routes.AnimatedOnboarding> { inclusive = true }
                    }
                }
            )
        }

        composable<Routes.VerticalPagerOnboarding> {
            VerticalPagerOnboardingScreen(
                onFinished = {
                    navController.navigate(Routes.Success("Vertical Pager Onboarding")) {
                        popUpTo<Routes.VerticalPagerOnboarding> { inclusive = true }
                    }
                }
            )
        }

        composable<Routes.ParallaxOnboarding> {
            ParallaxOnboardingScreen(
                onFinished = {
                    navController.navigate(Routes.Success("Parallax Onboarding")) {
                        popUpTo<Routes.ParallaxOnboarding> { inclusive = true }
                    }
                }
            )
        }

        composable<Routes.CardStackOnboarding> {
            CardStackOnboardingScreen(
                onFinished = {
                    navController.navigate(Routes.Success("Card Stack Onboarding")) {
                        popUpTo<Routes.CardStackOnboarding> { inclusive = true }
                    }
                }
            )
        }

        composable<Routes.BottomSheetOnboarding> {
            BottomSheetOnboardingScreen(
                onFinished = {
                    navController.navigate(Routes.Success("Bottom Sheet Onboarding")) {
                        popUpTo<Routes.BottomSheetOnboarding> { inclusive = true }
                    }
                }
            )
        }

        composable<Routes.FloatingOnboarding> {
            FloatingOnboardingScreen(
                onFinished = {
                    navController.navigate(Routes.Success("Floating Onboarding")) {
                        popUpTo<Routes.FloatingOnboarding> { inclusive = true }
                    }
                }
            )
        }

        composable<Routes.GradientOnboarding> {
            GradientOnboardingScreen(
                onFinished = {
                    navController.navigate(Routes.Success("Gradient Onboarding")) {
                        popUpTo<Routes.GradientOnboarding> { inclusive = true }
                    }
                }
            )
        }

        composable<Routes.SplitScreenOnboarding> {
            SplitScreenOnboardingScreen(
                onFinished = {
                    navController.navigate(Routes.Success("Split Screen Onboarding")) {
                        popUpTo<Routes.SplitScreenOnboarding> { inclusive = true }
                    }
                }
            )
        }

        composable<Routes.TimelineOnboarding> {
            TimelineOnboardingScreen(
                onFinished = {
                    navController.navigate(Routes.Success("Timeline Onboarding")) {
                        popUpTo<Routes.TimelineOnboarding> { inclusive = true }
                    }
                }
            )
        }

        composable<Routes.StepperOnboarding> {
            StepperOnboardingScreen(
                onFinished = {
                    navController.navigate(Routes.Success("Stepper Onboarding")) {
                        popUpTo<Routes.StepperOnboarding> { inclusive = true }
                    }
                }
            )
        }

        composable<Routes.VideoBackgroundOnboarding> {
            VideoBackgroundOnboardingScreen(
                onFinished = {
                    navController.navigate(Routes.Success("Video Background Onboarding")) {
                        popUpTo<Routes.VideoBackgroundOnboarding> { inclusive = true }
                    }
                }
            )
        }

        composable<Routes.MorphingOnboarding> {
            MorphingOnboardingScreen(
                onFinished = {
                    navController.navigate(Routes.Success("Morphing Onboarding")) {
                        popUpTo<Routes.MorphingOnboarding> { inclusive = true }
                    }
                }
            )
        }

        composable<Routes.ZoomTransitionOnboarding> {
            ZoomTransitionOnboardingScreen(
                onFinished = {
                    navController.navigate(Routes.Success("Zoom Transition Onboarding")) {
                        popUpTo<Routes.ZoomTransitionOnboarding> { inclusive = true }
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
