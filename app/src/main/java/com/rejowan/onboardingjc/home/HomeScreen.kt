package com.rejowan.onboardingjc.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Notes
import androidx.compose.material.icons.automirrored.filled.ViewList
import androidx.compose.material.icons.filled.Adjust
import androidx.compose.material.icons.filled.Animation
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.AutoStories
import androidx.compose.material.icons.filled.BlurOn
import androidx.compose.material.icons.filled.Celebration
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.DragHandle
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.FilterNone
import androidx.compose.material.icons.filled.Flip
import androidx.compose.material.icons.filled.FormatListNumbered
import androidx.compose.material.icons.filled.Fullscreen
import androidx.compose.material.icons.filled.Gradient
import androidx.compose.material.icons.filled.Keyboard
import androidx.compose.material.icons.filled.Layers
import androidx.compose.material.icons.filled.Lens
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material.icons.filled.RotateRight
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.SlowMotionVideo
import androidx.compose.material.icons.filled.SwipeRight
import androidx.compose.material.icons.filled.SwipeUp
import androidx.compose.material.icons.filled.SwipeVertical
import androidx.compose.material.icons.filled.Timeline
import androidx.compose.material.icons.filled.TouchApp
import androidx.compose.material.icons.filled.ViewAgenda
import androidx.compose.material.icons.filled.ViewInAr
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material.icons.filled.ZoomOutMap
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rejowan.onboardingjc.components.VariationCard
import com.rejowan.onboardingjc.navigation.Routes

@Composable
fun HomeScreen(
    onVariationClick: (Routes) -> Unit,
    onSettingsClick: () -> Unit = {},
    listState: LazyListState = rememberLazyListState()
) {
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current
    var searchQuery by rememberSaveable { mutableStateOf("") }

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
                isImplemented = true
            ),
            OnboardingVariation(
                id = "vertical_pager",
                name = "Vertical Pager",
                description = "Swipe up/down instead of left/right navigation",
                icon = Icons.Default.SwipeVertical,
                hasTutorial = false,
                tutorialUrl = null,
                route = Routes.VerticalPagerOnboarding,
                isImplemented = true
            ),
            OnboardingVariation(
                id = "parallax",
                name = "Parallax",
                description = "Multi-layer parallax scrolling effect on images",
                icon = Icons.Default.Layers,
                hasTutorial = false,
                tutorialUrl = null,
                route = Routes.ParallaxOnboarding,
                isImplemented = true
            ),
            OnboardingVariation(
                id = "card_stack",
                name = "Card Stack",
                description = "Cards that stack/unstack as you progress through pages",
                icon = Icons.Default.ViewAgenda,
                hasTutorial = false,
                tutorialUrl = null,
                route = Routes.CardStackOnboarding,
                isImplemented = true
            ),
            OnboardingVariation(
                id = "bottom_sheet",
                name = "Bottom Sheet",
                description = "Content slides up from bottom sheet style overlay",
                icon = Icons.Default.DragHandle,
                hasTutorial = false,
                tutorialUrl = null,
                route = Routes.BottomSheetOnboarding,
                isImplemented = true
            ),
            OnboardingVariation(
                id = "floating",
                name = "Floating",
                description = "Floating cards with shadow and centered content",
                icon = Icons.Default.FilterNone,
                hasTutorial = false,
                tutorialUrl = null,
                route = Routes.FloatingOnboarding,
                isImplemented = true
            ),
            OnboardingVariation(
                id = "gradient",
                name = "Gradient",
                description = "Dynamic gradient backgrounds that change per page",
                icon = Icons.Default.Gradient,
                hasTutorial = false,
                tutorialUrl = null,
                route = Routes.GradientOnboarding,
                isImplemented = true
            ),
            OnboardingVariation(
                id = "split_screen",
                name = "Split Screen",
                description = "Image on top half, text and buttons on bottom half",
                icon = Icons.Default.SwipeUp,
                hasTutorial = false,
                tutorialUrl = null,
                route = Routes.SplitScreenOnboarding,
                isImplemented = true
            ),
            OnboardingVariation(
                id = "timeline",
                name = "Timeline",
                description = "Progress shown as vertical timeline on the side",
                icon = Icons.Default.Timeline,
                hasTutorial = false,
                tutorialUrl = null,
                route = Routes.TimelineOnboarding,
                isImplemented = true
            ),
            OnboardingVariation(
                id = "stepper",
                name = "Stepper",
                description = "Step-by-step with numbered progress indicator",
                icon = Icons.Default.FormatListNumbered,
                hasTutorial = false,
                tutorialUrl = null,
                route = Routes.StepperOnboarding,
                isImplemented = true
            ),
            OnboardingVariation(
                id = "video_background",
                name = "Video Background",
                description = "Animated gradient or video playing in background",
                icon = Icons.Default.SlowMotionVideo,
                hasTutorial = false,
                tutorialUrl = null,
                route = Routes.VideoBackgroundOnboarding,
                isImplemented = true
            ),
            OnboardingVariation(
                id = "morphing",
                name = "Morphing",
                description = "Shapes and elements that morph between pages",
                icon = Icons.Default.AutoAwesome,
                hasTutorial = false,
                tutorialUrl = null,
                route = Routes.MorphingOnboarding,
                isImplemented = true
            ),
            OnboardingVariation(
                id = "zoom_transition",
                name = "Zoom Transition",
                description = "Zoom in/out transitions between pages",
                icon = Icons.Default.ZoomOutMap,
                hasTutorial = false,
                tutorialUrl = null,
                route = Routes.ZoomTransitionOnboarding,
                isImplemented = true
            ),
            OnboardingVariation(
                id = "carousel_3d",
                name = "3D Carousel",
                description = "Cards rotate in 3D space with perspective",
                icon = Icons.Default.Public,
                hasTutorial = false,
                tutorialUrl = null,
                route = Routes.Carousel3DOnboarding,
                isImplemented = true
            ),
            OnboardingVariation(
                id = "card_flip",
                name = "Card Flip",
                description = "Pages flip like a book with 3D rotation",
                icon = Icons.Default.Flip,
                hasTutorial = false,
                tutorialUrl = null,
                route = Routes.CardFlipOnboarding,
                isImplemented = true
            ),
            OnboardingVariation(
                id = "cube_transition",
                name = "Cube Transition",
                description = "3D cube rotation between pages",
                icon = Icons.Default.ViewInAr,
                hasTutorial = false,
                tutorialUrl = null,
                route = Routes.CubeTransitionOnboarding,
                isImplemented = true
            ),
            OnboardingVariation(
                id = "fade_crossfade",
                name = "Fade Crossfade",
                description = "Smooth crossfade transitions between pages",
                icon = Icons.Default.BlurOn,
                hasTutorial = false,
                tutorialUrl = null,
                route = Routes.FadeCrossfadeOnboarding,
                isImplemented = true
            ),
            OnboardingVariation(
                id = "glassmorphism",
                name = "Glassmorphism",
                description = "Frosted glass effect UI with blur",
                icon = Icons.Default.Lens,
                hasTutorial = false,
                tutorialUrl = null,
                route = Routes.GlassmorphismOnboarding,
                isImplemented = true
            ),
            OnboardingVariation(
                id = "neumorphism",
                name = "Neumorphism",
                description = "Soft UI with inset/outset shadows",
                icon = Icons.Default.TouchApp,
                hasTutorial = false,
                tutorialUrl = null,
                route = Routes.NeumorphismOnboarding,
                isImplemented = true
            ),
            OnboardingVariation(
                id = "typewriter",
                name = "Typewriter",
                description = "Text typing animation effect",
                icon = Icons.Default.Keyboard,
                hasTutorial = false,
                tutorialUrl = null,
                route = Routes.TypewriterOnboarding,
                isImplemented = true
            ),
            OnboardingVariation(
                id = "swipe_cards",
                name = "Swipe Cards",
                description = "Tinder-style swipe left/right cards",
                icon = Icons.Default.SwipeRight,
                hasTutorial = false,
                tutorialUrl = null,
                route = Routes.SwipeCardsOnboarding,
                isImplemented = true
            ),
            OnboardingVariation(
                id = "circular_reveal",
                name = "Circular Reveal",
                description = "Content reveals in circular motion",
                icon = Icons.Default.Adjust,
                hasTutorial = false,
                tutorialUrl = null,
                route = Routes.CircularRevealOnboarding,
                isImplemented = true
            ),
            OnboardingVariation(
                id = "pulsating",
                name = "Pulsating",
                description = "Elements pulse and breathe with life",
                icon = Icons.Default.FavoriteBorder,
                hasTutorial = false,
                tutorialUrl = null,
                route = Routes.PulsatingOnboarding,
                isImplemented = true
            ),
            OnboardingVariation(
                id = "particle",
                name = "Particle",
                description = "Floating particles create magical atmosphere",
                icon = Icons.Default.Celebration,
                hasTutorial = false,
                tutorialUrl = null,
                route = Routes.ParticleOnboarding,
                isImplemented = true
            ),
            OnboardingVariation(
                id = "liquid_blob",
                name = "Liquid Blob",
                description = "Liquid blob shape transitions",
                icon = Icons.Default.WaterDrop,
                hasTutorial = false,
                tutorialUrl = null,
                route = Routes.LiquidBlobOnboarding,
                isImplemented = true
            ),
            OnboardingVariation(
                id = "storytelling",
                name = "Storytelling",
                description = "Chapter-based narrative flow",
                icon = Icons.Default.AutoStories,
                hasTutorial = false,
                tutorialUrl = null,
                route = Routes.StorytellingOnboarding,
                isImplemented = true
            ),
            OnboardingVariation(
                id = "interactive_quiz",
                name = "Interactive Quiz",
                description = "Questions during onboarding",
                icon = Icons.Default.Quiz,
                hasTutorial = false,
                tutorialUrl = null,
                route = Routes.InteractiveQuizOnboarding,
                isImplemented = true
            ),
            OnboardingVariation(
                id = "chat_bubbles",
                name = "Chat Bubbles",
                description = "Conversational chat-style intro",
                icon = Icons.Default.Chat,
                hasTutorial = false,
                tutorialUrl = null,
                route = Routes.ChatBubblesOnboarding,
                isImplemented = true
            ),
            OnboardingVariation(
                id = "rotating_wheel",
                name = "Rotating Wheel",
                description = "Wheel/dial page selector",
                icon = Icons.Default.RotateRight,
                hasTutorial = false,
                tutorialUrl = null,
                route = Routes.RotatingWheelOnboarding,
                isImplemented = true
            )
        )
    }

    val filteredVariations by remember(searchQuery) {
        derivedStateOf {
            if (searchQuery.isBlank()) {
                variations
            } else {
                variations.filter {
                    it.name.contains(searchQuery, ignoreCase = true) ||
                            it.description.contains(searchQuery, ignoreCase = true)
                }
            }
        }
    }

    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) {
                    focusManager.clearFocus()
                    keyboardController?.hide()
                }
        ) {
            // Header
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Onboarding Showcase",
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    IconButton(onClick = onSettingsClick) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "Settings",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Explore ${variations.size} different onboarding screen variations",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Search bar
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text("Search variations...") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search"
                        )
                    },
                    trailingIcon = {
                        if (searchQuery.isNotEmpty()) {
                            IconButton(
                                onClick = {
                                    searchQuery = ""
                                    focusManager.clearFocus()
                                    keyboardController?.hide()
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Clear,
                                    contentDescription = "Clear"
                                )
                            }
                        }
                    },
                    singleLine = true,
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant,
                        focusedBorderColor = MaterialTheme.colorScheme.primary
                    ),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                    keyboardActions = KeyboardActions(
                        onSearch = {
                            focusManager.clearFocus()
                            keyboardController?.hide()
                        }
                    )
                )
            }

            HorizontalDivider()

            // Variations list
            if (filteredVariations.isEmpty()) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(32.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "No variations found",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Try a different search term",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            } else {
                LazyColumn(
                    state = listState,
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(filteredVariations, key = { it.id }) { variation ->
                        VariationCard(
                            variation = variation,
                            onClick = {
                                focusManager.clearFocus()
                                keyboardController?.hide()
                                onVariationClick(variation.route)
                            }
                        )
                    }
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
