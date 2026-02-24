package com.rejowan.onboardingjc.navigation

import androidx.compose.foundation.lazy.rememberLazyListState
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
import com.rejowan.onboardingjc.onboarding.carousel3d.Carousel3DOnboardingScreen
import com.rejowan.onboardingjc.onboarding.cardflip.CardFlipOnboardingScreen
import com.rejowan.onboardingjc.onboarding.cubetransition.CubeTransitionOnboardingScreen
import com.rejowan.onboardingjc.onboarding.fadecrossfade.FadeCrossfadeOnboardingScreen
import com.rejowan.onboardingjc.onboarding.glassmorphism.GlassmorphismOnboardingScreen
import com.rejowan.onboardingjc.onboarding.neumorphism.NeumorphismOnboardingScreen
import com.rejowan.onboardingjc.onboarding.typewriter.TypewriterOnboardingScreen
import com.rejowan.onboardingjc.onboarding.swipecards.SwipeCardsOnboardingScreen
import com.rejowan.onboardingjc.onboarding.circularreveal.CircularRevealOnboardingScreen
import com.rejowan.onboardingjc.onboarding.pulsating.PulsatingOnboardingScreen
import com.rejowan.onboardingjc.onboarding.particle.ParticleOnboardingScreen
import com.rejowan.onboardingjc.onboarding.liquidblob.LiquidBlobOnboardingScreen
import com.rejowan.onboardingjc.onboarding.storytelling.StorytellingOnboardingScreen
import com.rejowan.onboardingjc.onboarding.interactivequiz.InteractiveQuizOnboardingScreen
import com.rejowan.onboardingjc.onboarding.chatbubbles.ChatBubblesOnboardingScreen
import com.rejowan.onboardingjc.onboarding.rotatingwheel.RotatingWheelOnboardingScreen
import com.rejowan.onboardingjc.success.SuccessScreen

@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController()
) {
    // Remember list state at this level to preserve scroll position
    val homeListState = rememberLazyListState()

    NavHost(
        navController = navController,
        startDestination = Routes.Home
    ) {
        composable<Routes.Home> {
            HomeScreen(
                onVariationClick = { route ->
                    navController.navigate(route)
                },
                listState = homeListState
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

        composable<Routes.Carousel3DOnboarding> {
            Carousel3DOnboardingScreen(
                onFinished = {
                    navController.navigate(Routes.Success("3D Carousel Onboarding")) {
                        popUpTo<Routes.Carousel3DOnboarding> { inclusive = true }
                    }
                }
            )
        }

        composable<Routes.CardFlipOnboarding> {
            CardFlipOnboardingScreen(
                onFinished = {
                    navController.navigate(Routes.Success("Card Flip Onboarding")) {
                        popUpTo<Routes.CardFlipOnboarding> { inclusive = true }
                    }
                }
            )
        }

        composable<Routes.CubeTransitionOnboarding> {
            CubeTransitionOnboardingScreen(
                onFinished = {
                    navController.navigate(Routes.Success("Cube Transition Onboarding")) {
                        popUpTo<Routes.CubeTransitionOnboarding> { inclusive = true }
                    }
                }
            )
        }

        composable<Routes.FadeCrossfadeOnboarding> {
            FadeCrossfadeOnboardingScreen(
                onFinished = {
                    navController.navigate(Routes.Success("Fade Crossfade Onboarding")) {
                        popUpTo<Routes.FadeCrossfadeOnboarding> { inclusive = true }
                    }
                }
            )
        }

        composable<Routes.GlassmorphismOnboarding> {
            GlassmorphismOnboardingScreen(
                onFinished = {
                    navController.navigate(Routes.Success("Glassmorphism Onboarding")) {
                        popUpTo<Routes.GlassmorphismOnboarding> { inclusive = true }
                    }
                }
            )
        }

        composable<Routes.NeumorphismOnboarding> {
            NeumorphismOnboardingScreen(
                onFinished = {
                    navController.navigate(Routes.Success("Neumorphism Onboarding")) {
                        popUpTo<Routes.NeumorphismOnboarding> { inclusive = true }
                    }
                }
            )
        }

        composable<Routes.TypewriterOnboarding> {
            TypewriterOnboardingScreen(
                onFinished = {
                    navController.navigate(Routes.Success("Typewriter Onboarding")) {
                        popUpTo<Routes.TypewriterOnboarding> { inclusive = true }
                    }
                }
            )
        }

        composable<Routes.SwipeCardsOnboarding> {
            SwipeCardsOnboardingScreen(
                onFinished = {
                    navController.navigate(Routes.Success("Swipe Cards Onboarding")) {
                        popUpTo<Routes.SwipeCardsOnboarding> { inclusive = true }
                    }
                }
            )
        }

        composable<Routes.CircularRevealOnboarding> {
            CircularRevealOnboardingScreen(
                onFinished = {
                    navController.navigate(Routes.Success("Circular Reveal Onboarding")) {
                        popUpTo<Routes.CircularRevealOnboarding> { inclusive = true }
                    }
                }
            )
        }

        composable<Routes.PulsatingOnboarding> {
            PulsatingOnboardingScreen(
                onFinished = {
                    navController.navigate(Routes.Success("Pulsating Onboarding")) {
                        popUpTo<Routes.PulsatingOnboarding> { inclusive = true }
                    }
                }
            )
        }

        composable<Routes.ParticleOnboarding> {
            ParticleOnboardingScreen(
                onFinished = {
                    navController.navigate(Routes.Success("Particle Onboarding")) {
                        popUpTo<Routes.ParticleOnboarding> { inclusive = true }
                    }
                }
            )
        }

        composable<Routes.LiquidBlobOnboarding> {
            LiquidBlobOnboardingScreen(
                onFinished = {
                    navController.navigate(Routes.Success("Liquid Blob Onboarding")) {
                        popUpTo<Routes.LiquidBlobOnboarding> { inclusive = true }
                    }
                }
            )
        }

        composable<Routes.StorytellingOnboarding> {
            StorytellingOnboardingScreen(
                onFinished = {
                    navController.navigate(Routes.Success("Storytelling Onboarding")) {
                        popUpTo<Routes.StorytellingOnboarding> { inclusive = true }
                    }
                }
            )
        }

        composable<Routes.InteractiveQuizOnboarding> {
            InteractiveQuizOnboardingScreen(
                onFinished = {
                    navController.navigate(Routes.Success("Interactive Quiz Onboarding")) {
                        popUpTo<Routes.InteractiveQuizOnboarding> { inclusive = true }
                    }
                }
            )
        }

        composable<Routes.ChatBubblesOnboarding> {
            ChatBubblesOnboardingScreen(
                onFinished = {
                    navController.navigate(Routes.Success("Chat Bubbles Onboarding")) {
                        popUpTo<Routes.ChatBubblesOnboarding> { inclusive = true }
                    }
                }
            )
        }

        composable<Routes.RotatingWheelOnboarding> {
            RotatingWheelOnboardingScreen(
                onFinished = {
                    navController.navigate(Routes.Success("Rotating Wheel Onboarding")) {
                        popUpTo<Routes.RotatingWheelOnboarding> { inclusive = true }
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
