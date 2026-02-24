package com.rejowan.onboardingjc.onboarding.carousel3d

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
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Rocket
import androidx.compose.material.icons.filled.Stars
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.math.absoluteValue

data class Carousel3DPage(
    val icon: ImageVector,
    val title: String,
    val description: String
)

private val pages = listOf(
    Carousel3DPage(
        icon = Icons.Default.Explore,
        title = "Explore in 3D",
        description = "Navigate through cards with stunning 3D rotation effects"
    ),
    Carousel3DPage(
        icon = Icons.Default.Stars,
        title = "Immersive Design",
        description = "Experience depth and perspective in every transition"
    ),
    Carousel3DPage(
        icon = Icons.Default.Rocket,
        title = "Launch Your Journey",
        description = "Ready to explore the third dimension?"
    )
)

@Composable
fun Carousel3DOnboardingScreen(onFinished: () -> Unit) {
    var currentPage by remember { mutableIntStateOf(0) }

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
                Spacer(modifier = Modifier.height(48.dp))

                // 3D Carousel
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    pages.forEachIndexed { index, page ->
                        val offset = index - currentPage

                        val rotationY by animateFloatAsState(
                            targetValue = offset * 45f,
                            animationSpec = tween(500),
                            label = "rotationY"
                        )

                        val scale by animateFloatAsState(
                            targetValue = if (offset == 0) 1f else 0.8f,
                            animationSpec = tween(500),
                            label = "scale"
                        )

                        val alpha by animateFloatAsState(
                            targetValue = if (offset.absoluteValue <= 1) 1f - (offset.absoluteValue * 0.4f) else 0f,
                            animationSpec = tween(500),
                            label = "alpha"
                        )

                        val translationX by animateFloatAsState(
                            targetValue = offset * 200f,
                            animationSpec = tween(500),
                            label = "translationX"
                        )

                        val zIndex = if (offset == 0) 1f else 0f

                        if (offset.absoluteValue <= 1) {
                            Card(
                                modifier = Modifier
                                    .size(280.dp, 360.dp)
                                    .graphicsLayer {
                                        this.rotationY = rotationY
                                        this.scaleX = scale
                                        this.scaleY = scale
                                        this.alpha = alpha
                                        this.translationX = translationX
                                        this.cameraDistance = 12f * density
                                    },
                                shape = RoundedCornerShape(24.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = MaterialTheme.colorScheme.primaryContainer
                                ),
                                elevation = CardDefaults.cardElevation(
                                    defaultElevation = if (offset == 0) 16.dp else 4.dp
                                )
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(24.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(80.dp)
                                            .clip(CircleShape)
                                            .background(MaterialTheme.colorScheme.primary),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Icon(
                                            imageVector = page.icon,
                                            contentDescription = null,
                                            modifier = Modifier.size(40.dp),
                                            tint = MaterialTheme.colorScheme.onPrimary
                                        )
                                    }

                                    Spacer(modifier = Modifier.height(24.dp))

                                    Text(
                                        text = page.title,
                                        style = MaterialTheme.typography.headlineSmall,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                                        textAlign = TextAlign.Center
                                    )

                                    Spacer(modifier = Modifier.height(12.dp))

                                    Text(
                                        text = page.description,
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f),
                                        textAlign = TextAlign.Center
                                    )
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

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
                                        MaterialTheme.colorScheme.primary
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
fun Carousel3DOnboardingScreenPreview() {
    Carousel3DOnboardingScreen(onFinished = {})
}
