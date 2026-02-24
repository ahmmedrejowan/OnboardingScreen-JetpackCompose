package com.rejowan.onboardingjc.navigation

sealed class Routes(val route: String) {
    data object Home : Routes("home")

    // Onboarding variations
    data object ClassicOnboarding : Routes("classic_onboarding")
    data object FullscreenOnboarding : Routes("fullscreen_onboarding")
    data object MinimalistOnboarding : Routes("minimalist_onboarding")
    data object AnimatedOnboarding : Routes("animated_onboarding")
    data object VerticalPagerOnboarding : Routes("vertical_pager_onboarding")
    data object ParallaxOnboarding : Routes("parallax_onboarding")
    data object CardStackOnboarding : Routes("card_stack_onboarding")
    data object BottomSheetOnboarding : Routes("bottom_sheet_onboarding")
    data object FloatingOnboarding : Routes("floating_onboarding")
    data object GradientOnboarding : Routes("gradient_onboarding")
    data object SplitScreenOnboarding : Routes("split_screen_onboarding")
    data object TimelineOnboarding : Routes("timeline_onboarding")
    data object StepperOnboarding : Routes("stepper_onboarding")
    data object VideoBackgroundOnboarding : Routes("video_background_onboarding")
    data object MorphingOnboarding : Routes("morphing_onboarding")
    data object ZoomTransitionOnboarding : Routes("zoom_transition_onboarding")

    // Success screen
    data object Success : Routes("success/{onboardingName}") {
        fun createRoute(onboardingName: String) = "success/$onboardingName"
    }
}
