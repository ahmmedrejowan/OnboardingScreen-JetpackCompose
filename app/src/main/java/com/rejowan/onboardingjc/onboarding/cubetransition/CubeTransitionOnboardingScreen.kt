package com.rejowan.onboardingjc.onboarding.cubetransition

import androidx.compose.animation.core.Animatable
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
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.RotateLeft
import androidx.compose.material.icons.filled.ViewInAr
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

data class CubePage(
    val icon: ImageVector,
    val title: String,
    val description: String,
    val backgroundColor: Color
)

private val pages = listOf(
    CubePage(
        icon = Icons.Default.RotateLeft,
        title = "3D Cube Effect",
        description = "Rotate through content like faces of a cube",
        backgroundColor = Color(0xFF6366F1)
    ),
    CubePage(
        icon = Icons.Default.ViewInAr,
        title = "Spatial Navigation",
        description = "Experience depth in every transition",
        backgroundColor = Color(0xFF8B5CF6)
    ),
    CubePage(
        icon = Icons.Default.Category,
        title = "Dimensional Design",
        description = "Ready to think outside the box?",
        backgroundColor = Color(0xFFA855F7)
    )
)

@Composable
fun CubeTransitionOnboardingScreen(onFinished: () -> Unit) {
    var currentPage by remember { mutableIntStateOf(0) }
    var isAnimating by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    // Animation progress: 0 = showing current, 1 = showing next/prev
    val animationProgress = remember { Animatable(0f) }
    var animationDirection by remember { mutableIntStateOf(0) } // 1 = forward, -1 = backward

    fun animateToPage(targetPage: Int) {
        if (isAnimating) return
        if (targetPage < 0 || targetPage >= pages.size) return

        isAnimating = true
        animationDirection = if (targetPage > currentPage) 1 else -1

        scope.launch {
            animationProgress.animateTo(
                targetValue = 1f,
                animationSpec = tween(500)
            )
            currentPage = targetPage
            animationProgress.snapTo(0f)
            isAnimating = false
        }
    }

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

                // Cube container
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    val progress = animationProgress.value
                    val nextPage = (currentPage + animationDirection).coerceIn(0, pages.size - 1)

                    // Outgoing face (current page rotating away)
                    if (progress < 1f) {
                        Box(
                            modifier = Modifier
                                .size(280.dp, 350.dp)
                                .graphicsLayer {
                                    cameraDistance = 12f * density
                                    rotationY = progress * 90f * animationDirection
                                    alpha = 1f - progress
                                }
                        ) {
                            CubeFace(
                                page = pages[currentPage],
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    }

                    // Incoming face (next page rotating in)
                    if (progress > 0f && animationDirection != 0) {
                        Box(
                            modifier = Modifier
                                .size(280.dp, 350.dp)
                                .graphicsLayer {
                                    cameraDistance = 12f * density
                                    rotationY = (1f - progress) * -90f * animationDirection
                                    alpha = progress
                                }
                        ) {
                            CubeFace(
                                page = pages[nextPage],
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                // Title and description outside cube
                Text(
                    text = pages[currentPage].title,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = pages[currentPage].description,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 24.dp)
                )

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
                                        pages[currentPage].backgroundColor
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
                        TextButton(
                            onClick = { animateToPage(currentPage - 1) }
                        ) {
                            Text("Back")
                        }
                    } else {
                        Spacer(modifier = Modifier.weight(1f))
                    }

                    Button(
                        onClick = {
                            if (currentPage < pages.size - 1) {
                                animateToPage(currentPage + 1)
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

@Composable
private fun CubeFace(
    page: CubePage,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(24.dp))
            .background(page.backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(Color.White.copy(alpha = 0.2f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = page.icon,
                    contentDescription = null,
                    modifier = Modifier.size(50.dp),
                    tint = Color.White
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CubeTransitionOnboardingScreenPreview() {
    CubeTransitionOnboardingScreen(onFinished = {})
}
