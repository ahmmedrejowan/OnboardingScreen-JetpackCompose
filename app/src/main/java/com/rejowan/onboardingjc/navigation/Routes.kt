package com.rejowan.onboardingjc.navigation

import kotlinx.serialization.Serializable

sealed interface Routes {
    @Serializable
    data object Home : Routes

    @Serializable
    data object Settings : Routes

    // Onboarding variations
    @Serializable
    data object ClassicOnboarding : Routes

    @Serializable
    data object FullscreenOnboarding : Routes

    @Serializable
    data object MinimalistOnboarding : Routes

    @Serializable
    data object AnimatedOnboarding : Routes

    @Serializable
    data object VerticalPagerOnboarding : Routes

    @Serializable
    data object ParallaxOnboarding : Routes

    @Serializable
    data object CardStackOnboarding : Routes

    @Serializable
    data object BottomSheetOnboarding : Routes

    @Serializable
    data object FloatingOnboarding : Routes

    @Serializable
    data object GradientOnboarding : Routes

    @Serializable
    data object SplitScreenOnboarding : Routes

    @Serializable
    data object TimelineOnboarding : Routes

    @Serializable
    data object StepperOnboarding : Routes

    @Serializable
    data object VideoBackgroundOnboarding : Routes

    @Serializable
    data object MorphingOnboarding : Routes

    @Serializable
    data object ZoomTransitionOnboarding : Routes

    @Serializable
    data object Carousel3DOnboarding : Routes

    @Serializable
    data object CardFlipOnboarding : Routes

    @Serializable
    data object CubeTransitionOnboarding : Routes

    @Serializable
    data object FadeCrossfadeOnboarding : Routes

    @Serializable
    data object GlassmorphismOnboarding : Routes

    @Serializable
    data object NeumorphismOnboarding : Routes

    @Serializable
    data object TypewriterOnboarding : Routes

    @Serializable
    data object SwipeCardsOnboarding : Routes

    @Serializable
    data object CircularRevealOnboarding : Routes

    @Serializable
    data object PulsatingOnboarding : Routes

    @Serializable
    data object ParticleOnboarding : Routes

    @Serializable
    data object LiquidBlobOnboarding : Routes

    @Serializable
    data object StorytellingOnboarding : Routes

    @Serializable
    data object InteractiveQuizOnboarding : Routes

    @Serializable
    data object ChatBubblesOnboarding : Routes

    @Serializable
    data object RotatingWheelOnboarding : Routes

    // Success screen
    @Serializable
    data class Success(val onboardingName: String) : Routes
}
