package com.rejowan.onboardingjc.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rejowan.onboardingjc.R
import com.rejowan.onboardingjc.components.VariationCard
import com.rejowan.onboardingjc.navigation.Routes

@Composable
fun HomeScreen(
    onVariationClick: (String) -> Unit
) {
    val variations = remember {
        listOf(
            OnboardingVariation(
                id = "classic",
                name = "Classic",
                description = "Horizontal pager with dots indicator and back/next buttons",
                previewImage = R.drawable.img_into_1,
                hasTutorial = true,
                tutorialUrl = "https://youtube.com/...",
                route = Routes.ClassicOnboarding.route,
                isImplemented = true
            ),
            OnboardingVariation(
                id = "fullscreen",
                name = "Fullscreen",
                description = "Full-bleed background images with gradient overlay, text at bottom",
                previewImage = R.drawable.img_into_2,
                hasTutorial = false,
                tutorialUrl = null,
                route = Routes.FullscreenOnboarding.route,
                isImplemented = false
            ),
            OnboardingVariation(
                id = "minimalist",
                name = "Minimalist",
                description = "Clean, text-focused design with simple icons and whitespace",
                previewImage = R.drawable.img_into_3,
                hasTutorial = false,
                tutorialUrl = null,
                route = Routes.MinimalistOnboarding.route,
                isImplemented = false
            ),
            OnboardingVariation(
                id = "animated",
                name = "Animated (Lottie)",
                description = "Uses Lottie JSON animations instead of static images",
                previewImage = R.drawable.img_into_1,
                hasTutorial = false,
                tutorialUrl = null,
                route = Routes.AnimatedOnboarding.route,
                isImplemented = false
            ),
            OnboardingVariation(
                id = "vertical_pager",
                name = "Vertical Pager",
                description = "Swipe up/down instead of left/right navigation",
                previewImage = R.drawable.img_into_2,
                hasTutorial = false,
                tutorialUrl = null,
                route = Routes.VerticalPagerOnboarding.route,
                isImplemented = false
            ),
            OnboardingVariation(
                id = "parallax",
                name = "Parallax",
                description = "Multi-layer parallax scrolling effect on images",
                previewImage = R.drawable.img_into_3,
                hasTutorial = false,
                tutorialUrl = null,
                route = Routes.ParallaxOnboarding.route,
                isImplemented = false
            ),
            OnboardingVariation(
                id = "card_stack",
                name = "Card Stack",
                description = "Cards that stack/unstack as you progress through pages",
                previewImage = R.drawable.img_into_1,
                hasTutorial = false,
                tutorialUrl = null,
                route = Routes.CardStackOnboarding.route,
                isImplemented = false
            ),
            OnboardingVariation(
                id = "bottom_sheet",
                name = "Bottom Sheet",
                description = "Content slides up from bottom sheet style overlay",
                previewImage = R.drawable.img_into_2,
                hasTutorial = false,
                tutorialUrl = null,
                route = Routes.BottomSheetOnboarding.route,
                isImplemented = false
            ),
            OnboardingVariation(
                id = "floating",
                name = "Floating",
                description = "Floating cards with shadow and centered content",
                previewImage = R.drawable.img_into_3,
                hasTutorial = false,
                tutorialUrl = null,
                route = Routes.FloatingOnboarding.route,
                isImplemented = false
            ),
            OnboardingVariation(
                id = "gradient",
                name = "Gradient",
                description = "Dynamic gradient backgrounds that change per page",
                previewImage = R.drawable.img_into_1,
                hasTutorial = false,
                tutorialUrl = null,
                route = Routes.GradientOnboarding.route,
                isImplemented = false
            ),
            OnboardingVariation(
                id = "split_screen",
                name = "Split Screen",
                description = "Image on top half, text and buttons on bottom half",
                previewImage = R.drawable.img_into_2,
                hasTutorial = false,
                tutorialUrl = null,
                route = Routes.SplitScreenOnboarding.route,
                isImplemented = false
            ),
            OnboardingVariation(
                id = "timeline",
                name = "Timeline",
                description = "Progress shown as vertical timeline on the side",
                previewImage = R.drawable.img_into_3,
                hasTutorial = false,
                tutorialUrl = null,
                route = Routes.TimelineOnboarding.route,
                isImplemented = false
            ),
            OnboardingVariation(
                id = "stepper",
                name = "Stepper",
                description = "Step-by-step with numbered progress indicator",
                previewImage = R.drawable.img_into_1,
                hasTutorial = false,
                tutorialUrl = null,
                route = Routes.StepperOnboarding.route,
                isImplemented = false
            ),
            OnboardingVariation(
                id = "video_background",
                name = "Video Background",
                description = "Animated gradient or video playing in background",
                previewImage = R.drawable.img_into_2,
                hasTutorial = false,
                tutorialUrl = null,
                route = Routes.VideoBackgroundOnboarding.route,
                isImplemented = false
            ),
            OnboardingVariation(
                id = "morphing",
                name = "Morphing",
                description = "Shapes and elements that morph between pages",
                previewImage = R.drawable.img_into_3,
                hasTutorial = false,
                tutorialUrl = null,
                route = Routes.MorphingOnboarding.route,
                isImplemented = false
            ),
            OnboardingVariation(
                id = "zoom_transition",
                name = "Zoom Transition",
                description = "Zoom in/out transitions between pages",
                previewImage = R.drawable.img_into_1,
                hasTutorial = false,
                tutorialUrl = null,
                route = Routes.ZoomTransitionOnboarding.route,
                isImplemented = false
            )
        )
    }

    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Header
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Onboarding Showcase",
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Explore ${variations.size} different onboarding screen variations",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Divider()

            // Variations list
            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(variations) { variation ->
                    VariationCard(
                        variation = variation,
                        onClick = {
                            onVariationClick(variation.route)
                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(onVariationClick = {})
}
