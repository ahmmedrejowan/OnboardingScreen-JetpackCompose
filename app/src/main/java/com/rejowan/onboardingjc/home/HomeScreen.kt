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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Notes
import androidx.compose.material.icons.automirrored.filled.ViewList
import androidx.compose.material.icons.filled.Animation
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.DragHandle
import androidx.compose.material.icons.filled.FilterNone
import androidx.compose.material.icons.filled.FormatListNumbered
import androidx.compose.material.icons.filled.Fullscreen
import androidx.compose.material.icons.filled.Gradient
import androidx.compose.material.icons.filled.Layers
import androidx.compose.material.icons.filled.SlowMotionVideo
import androidx.compose.material.icons.filled.SwipeUp
import androidx.compose.material.icons.filled.SwipeVertical
import androidx.compose.material.icons.filled.Timeline
import androidx.compose.material.icons.filled.ViewAgenda
import androidx.compose.material.icons.filled.ZoomOutMap
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rejowan.onboardingjc.components.VariationCard
import com.rejowan.onboardingjc.navigation.Routes

@Composable
fun HomeScreen(
    onVariationClick: (Routes) -> Unit
) {
    val variations = remember {
        listOf(
            OnboardingVariation(
                id = "classic",
                name = "Classic",
                description = "Horizontal pager with dots indicator and back/next buttons",
                icon = Icons.AutoMirrored.Filled.ViewList,
                hasTutorial = true,
                tutorialUrl = "https://youtube.com/...",
                route = Routes.ClassicOnboarding,
                isImplemented = true
            ),
            OnboardingVariation(
                id = "fullscreen",
                name = "Fullscreen",
                description = "Full-bleed background images with gradient overlay, text at bottom",
                icon = Icons.Default.Fullscreen,
                hasTutorial = false,
                tutorialUrl = null,
                route = Routes.FullscreenOnboarding,
                isImplemented = true
            ),
            OnboardingVariation(
                id = "minimalist",
                name = "Minimalist",
                description = "Clean, text-focused design with simple icons and whitespace",
                icon = Icons.AutoMirrored.Filled.Notes,
                hasTutorial = false,
                tutorialUrl = null,
                route = Routes.MinimalistOnboarding,
                isImplemented = true
            ),
            OnboardingVariation(
                id = "animated",
                name = "Animated (Lottie)",
                description = "Uses Lottie JSON animations instead of static images",
                icon = Icons.Default.Animation,
                hasTutorial = false,
                tutorialUrl = null,
                route = Routes.AnimatedOnboarding,
                isImplemented = false
            ),
            OnboardingVariation(
                id = "vertical_pager",
                name = "Vertical Pager",
                description = "Swipe up/down instead of left/right navigation",
                icon = Icons.Default.SwipeVertical,
                hasTutorial = false,
                tutorialUrl = null,
                route = Routes.VerticalPagerOnboarding,
                isImplemented = false
            ),
            OnboardingVariation(
                id = "parallax",
                name = "Parallax",
                description = "Multi-layer parallax scrolling effect on images",
                icon = Icons.Default.Layers,
                hasTutorial = false,
                tutorialUrl = null,
                route = Routes.ParallaxOnboarding,
                isImplemented = false
            ),
            OnboardingVariation(
                id = "card_stack",
                name = "Card Stack",
                description = "Cards that stack/unstack as you progress through pages",
                icon = Icons.Default.ViewAgenda,
                hasTutorial = false,
                tutorialUrl = null,
                route = Routes.CardStackOnboarding,
                isImplemented = false
            ),
            OnboardingVariation(
                id = "bottom_sheet",
                name = "Bottom Sheet",
                description = "Content slides up from bottom sheet style overlay",
                icon = Icons.Default.DragHandle,
                hasTutorial = false,
                tutorialUrl = null,
                route = Routes.BottomSheetOnboarding,
                isImplemented = false
            ),
            OnboardingVariation(
                id = "floating",
                name = "Floating",
                description = "Floating cards with shadow and centered content",
                icon = Icons.Default.FilterNone,
                hasTutorial = false,
                tutorialUrl = null,
                route = Routes.FloatingOnboarding,
                isImplemented = false
            ),
            OnboardingVariation(
                id = "gradient",
                name = "Gradient",
                description = "Dynamic gradient backgrounds that change per page",
                icon = Icons.Default.Gradient,
                hasTutorial = false,
                tutorialUrl = null,
                route = Routes.GradientOnboarding,
                isImplemented = false
            ),
            OnboardingVariation(
                id = "split_screen",
                name = "Split Screen",
                description = "Image on top half, text and buttons on bottom half",
                icon = Icons.Default.SwipeUp,
                hasTutorial = false,
                tutorialUrl = null,
                route = Routes.SplitScreenOnboarding,
                isImplemented = false
            ),
            OnboardingVariation(
                id = "timeline",
                name = "Timeline",
                description = "Progress shown as vertical timeline on the side",
                icon = Icons.Default.Timeline,
                hasTutorial = false,
                tutorialUrl = null,
                route = Routes.TimelineOnboarding,
                isImplemented = false
            ),
            OnboardingVariation(
                id = "stepper",
                name = "Stepper",
                description = "Step-by-step with numbered progress indicator",
                icon = Icons.Default.FormatListNumbered,
                hasTutorial = false,
                tutorialUrl = null,
                route = Routes.StepperOnboarding,
                isImplemented = false
            ),
            OnboardingVariation(
                id = "video_background",
                name = "Video Background",
                description = "Animated gradient or video playing in background",
                icon = Icons.Default.SlowMotionVideo,
                hasTutorial = false,
                tutorialUrl = null,
                route = Routes.VideoBackgroundOnboarding,
                isImplemented = false
            ),
            OnboardingVariation(
                id = "morphing",
                name = "Morphing",
                description = "Shapes and elements that morph between pages",
                icon = Icons.Default.AutoAwesome,
                hasTutorial = false,
                tutorialUrl = null,
                route = Routes.MorphingOnboarding,
                isImplemented = false
            ),
            OnboardingVariation(
                id = "zoom_transition",
                name = "Zoom Transition",
                description = "Zoom in/out transitions between pages",
                icon = Icons.Default.ZoomOutMap,
                hasTutorial = false,
                tutorialUrl = null,
                route = Routes.ZoomTransitionOnboarding,
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

            HorizontalDivider()

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
