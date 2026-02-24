package com.rejowan.onboardingjc.onboarding.circularreveal

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Adjust
import androidx.compose.material.icons.filled.BlurCircular
import androidx.compose.material.icons.filled.RadioButtonChecked
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.ClipOp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.math.hypot

data class CircularRevealPage(
    val icon: ImageVector,
    val title: String,
    val description: String,
    val backgroundColor: Color
)

private val pages = listOf(
    CircularRevealPage(
        icon = Icons.Default.RadioButtonChecked,
        title = "Circular Reveal",
        description = "Content reveals in an expanding circle",
        backgroundColor = Color(0xFF6366F1)
    ),
    CircularRevealPage(
        icon = Icons.Default.BlurCircular,
        title = "Smooth Expansion",
        description = "Watch as new content unfolds before you",
        backgroundColor = Color(0xFFEC4899)
    ),
    CircularRevealPage(
        icon = Icons.Default.Adjust,
        title = "Focus Point",
        description = "Ready to reveal your potential?",
        backgroundColor = Color(0xFF10B981)
    )
)

@Composable
fun CircularRevealOnboardingScreen(onFinished: () -> Unit) {
    var currentPage by remember { mutableIntStateOf(0) }
    var isRevealing by remember { mutableStateOf(false) }
    var nextPage by remember { mutableIntStateOf(0) }

    val configuration = LocalConfiguration.current
    val density = LocalDensity.current
    val screenWidth = with(density) { configuration.screenWidthDp.dp.toPx() }
    val screenHeight = with(density) { configuration.screenHeightDp.dp.toPx() }
    val maxRadius = hypot(screenWidth, screenHeight)

    val revealProgress by animateFloatAsState(
        targetValue = if (isRevealing) 1f else 0f,
        animationSpec = tween(600),
        finishedListener = {
            if (isRevealing) {
                currentPage = nextPage
                isRevealing = false
            }
        },
        label = "reveal"
    )

    Box(modifier = Modifier.fillMaxSize()) {
        // Current page background
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(pages[currentPage].backgroundColor)
        )

        // Revealing next page
        if (isRevealing) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .drawWithContent {
                        val centerX = size.width / 2
                        val centerY = size.height / 2
                        val radius = maxRadius * revealProgress

                        clipPath(
                            Path().apply {
                                addOval(
                                    Rect(
                                        center = Offset(centerX, centerY),
                                        radius = radius
                                    )
                                )
                            }
                        ) {
                            this@drawWithContent.drawContent()
                        }
                    }
                    .background(pages[nextPage].backgroundColor)
            )
        }

        // Skip button
        TextButton(
            onClick = onFinished,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp)
        ) {
            Text("Skip", color = Color.White.copy(alpha = 0.8f))
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(1f))

            // Content
            val displayPage = if (isRevealing && revealProgress > 0.5f) nextPage else currentPage

            Box(
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .background(Color.White.copy(alpha = 0.2f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = pages[displayPage].icon,
                    contentDescription = null,
                    modifier = Modifier.size(60.dp),
                    tint = Color.White
                )
            }

            Spacer(modifier = Modifier.height(48.dp))

            Text(
                text = pages[displayPage].title,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = pages[displayPage].description,
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White.copy(alpha = 0.85f),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 24.dp)
            )

            Spacer(modifier = Modifier.weight(1f))

            // Indicators
            Row(
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(pages.size) { index ->
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 4.dp)
                            .size(if (index == currentPage) 24.dp else 8.dp, 8.dp)
                            .clip(CircleShape)
                            .background(
                                if (index == currentPage)
                                    Color.White
                                else
                                    Color.White.copy(alpha = 0.3f)
                            )
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Navigation
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                if (currentPage > 0) {
                    TextButton(
                        onClick = {
                            if (!isRevealing) {
                                nextPage = currentPage - 1
                                isRevealing = true
                            }
                        }
                    ) {
                        Text("Back", color = Color.White)
                    }
                } else {
                    Spacer(modifier = Modifier.weight(1f))
                }

                Button(
                    onClick = {
                        if (currentPage < pages.size - 1) {
                            if (!isRevealing) {
                                nextPage = currentPage + 1
                                isRevealing = true
                            }
                        } else {
                            onFinished()
                        }
                    },
                    shape = RoundedCornerShape(12.dp),
                    colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                        containerColor = Color.White.copy(alpha = 0.2f),
                        contentColor = Color.White
                    )
                ) {
                    Text(
                        text = if (currentPage == pages.size - 1) "Get Started" else "Next",
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CircularRevealOnboardingScreenPreview() {
    CircularRevealOnboardingScreen(onFinished = {})
}
