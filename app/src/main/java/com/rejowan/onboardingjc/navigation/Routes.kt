package com.rejowan.onboardingjc.navigation

import kotlinx.serialization.Serializable

sealed interface Routes {
    @Serializable
    data object Home : Routes

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

    // Success screen
    @Serializable
    data class Success(val onboardingName: String) : Routes
}
