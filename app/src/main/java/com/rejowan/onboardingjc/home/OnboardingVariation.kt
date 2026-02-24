package com.rejowan.onboardingjc.home

import androidx.annotation.DrawableRes

data class OnboardingVariation(
    val id: String,
    val name: String,
    val description: String,
    @DrawableRes val previewImage: Int,
    val hasTutorial: Boolean,
    val tutorialUrl: String?,
    val route: String
)
