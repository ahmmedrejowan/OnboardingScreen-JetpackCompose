package com.rejowan.onboardingjc.onboarding.pulsating

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
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
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Flare
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

data class PulsatingPage(
    val icon: ImageVector,
    val title: String,
    val description: String,
    val primaryColor: Color,
    val secondaryColor: Color
)

private val pages = listOf(
    PulsatingPage(
        icon = Icons.Default.FavoriteBorder,
        title = "Living Breathing",
        description = "Elements that pulse and breathe with life",
        primaryColor = Color(0xFFFF6B6B),
        secondaryColor = Color(0xFFFFE66D)
    ),
    PulsatingPage(
        icon = Icons.Default.Flare,
        title = "Energy Flow",
        description = "Feel the rhythm of dynamic animations",
        primaryColor = Color(0xFF4ECDC4),
        secondaryColor = Color(0xFF44CF9C)
    ),
    PulsatingPage(
        icon = Icons.Default.WbSunny,
        title = "Radiant Design",
        description = "Ready to feel the pulse?",
        primaryColor = Color(0xFFFFB347),
        secondaryColor = Color(0xFFFFCC33)
    )
)

@Composable
fun PulsatingOnboardingScreen(onFinished: () -> Unit) {
    var currentPage by remember { mutableIntStateOf(0) }

    val infiniteTransition = rememberInfiniteTransition(label = "pulse")

    val pulseScale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.2f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulseScale"
    )

    val pulseAlpha by infiniteTransition.animateFloat(
        initialValue = 0.6f,
        targetValue = 0.2f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulseAlpha"
    )

    val outerPulseScale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.5f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "outerPulseScale"
    )

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            // Skip button
            TextButton(
                onClick = onFinished,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(16.dp)
            ) {
                Text("Skip")
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(60.dp))

                // Pulsating icon container
                Box(
                    modifier = Modifier.size(200.dp),
                    contentAlignment = Alignment.Center
                ) {
                    // Outer pulse ring
                    Box(
                        modifier = Modifier
                            .size(180.dp)
                            .scale(outerPulseScale)
                            .clip(CircleShape)
                            .background(
                                pages[currentPage].primaryColor.copy(alpha = pulseAlpha * 0.3f)
                            )
                    )

                    // Middle pulse ring
                    Box(
                        modifier = Modifier
                            .size(140.dp)
                            .scale(pulseScale)
                            .clip(CircleShape)
                            .background(
                                pages[currentPage].primaryColor.copy(alpha = pulseAlpha)
                            )
                    )

                    // Inner circle with icon
                    Box(
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape)
                            .background(pages[currentPage].primaryColor),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = pages[currentPage].icon,
                            contentDescription = null,
                            modifier = Modifier
                                .size(50.dp)
                                .scale(1f + (pulseScale - 1f) * 0.3f),
                            tint = Color.White
                        )
                    }
                }

                Spacer(modifier = Modifier.height(48.dp))

                // Title with subtle pulse
                Text(
                    text = pages[currentPage].title,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.scale(1f + (pulseScale - 1f) * 0.05f)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = pages[currentPage].description,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 24.dp)
                )

                Spacer(modifier = Modifier.weight(1f))

                // Pulsating indicators
                Row(
                    horizontalArrangement = Arrangement.Center
                ) {
                    repeat(pages.size) { index ->
                        val indicatorScale = if (index == currentPage) pulseScale else 1f

                        Box(
                            modifier = Modifier
                                .padding(horizontal = 6.dp)
                                .size(12.dp)
                                .scale(indicatorScale)
                                .clip(CircleShape)
                                .background(
                                    if (index == currentPage)
                                        pages[currentPage].primaryColor
                                    else
                                        MaterialTheme.colorScheme.outlineVariant
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
                        TextButton(onClick = { currentPage-- }) {
                            Text("Back")
                        }
                    } else {
                        Spacer(modifier = Modifier.weight(1f))
                    }

                    Button(
                        onClick = {
                            if (currentPage < pages.size - 1) {
                                currentPage++
                            } else {
                                onFinished()
                            }
                        },
                        shape = RoundedCornerShape(12.dp)
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
}

@Preview(showBackground = true)
@Composable
fun PulsatingOnboardingScreenPreview() {
    PulsatingOnboardingScreen(onFinished = {})
}
