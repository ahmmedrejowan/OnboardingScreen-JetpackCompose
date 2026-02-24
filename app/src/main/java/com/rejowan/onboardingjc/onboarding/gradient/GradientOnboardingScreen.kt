package com.rejowan.onboardingjc.onboarding.gradient

import androidx.compose.animation.animateColorAsState
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
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

data class GradientPage(
    val icon: ImageVector,
    val title: String,
    val description: String,
    val gradientColors: List<Color>
)

private val pages = listOf(
    GradientPage(
        icon = Icons.Default.Lightbulb,
        title = "Dynamic Gradients",
        description = "Watch the background colors flow and change as you navigate",
        gradientColors = listOf(Color(0xFF667eea), Color(0xFF764ba2))
    ),
    GradientPage(
        icon = Icons.Default.Favorite,
        title = "Colorful Journey",
        description = "Each page brings a new vibrant color palette",
        gradientColors = listOf(Color(0xFFf093fb), Color(0xFFf5576c))
    ),
    GradientPage(
        icon = Icons.Default.Star,
        title = "Ready to Shine",
        description = "Start your colorful adventure today",
        gradientColors = listOf(Color(0xFF4facfe), Color(0xFF00f2fe))
    )
)

@Composable
fun GradientOnboardingScreen(onFinished: () -> Unit) {
    val pagerState = rememberPagerState(initialPage = 0) { pages.size }
    val scope = rememberCoroutineScope()

    val currentColors = pages[pagerState.currentPage].gradientColors
    val animatedColor1 by animateColorAsState(
        targetValue = currentColors[0],
        animationSpec = tween(500),
        label = "color1"
    )
    val animatedColor2 by animateColorAsState(
        targetValue = currentColors[1],
        animationSpec = tween(500),
        label = "color2"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(animatedColor1, animatedColor2)
                )
            )
    ) {
        // Skip button
        TextButton(
            onClick = onFinished,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp)
        ) {
            Text(
                text = "Skip",
                color = Color.White.copy(alpha = 0.8f)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(1f))

            // Pager
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.weight(3f)
            ) { index ->
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    // Icon
                    Box(
                        modifier = Modifier
                            .size(120.dp)
                            .clip(CircleShape)
                            .background(Color.White.copy(alpha = 0.2f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = pages[index].icon,
                            contentDescription = null,
                            modifier = Modifier.size(56.dp),
                            tint = Color.White
                        )
                    }

                    Spacer(modifier = Modifier.height(48.dp))

                    // Title
                    Text(
                        text = pages[index].title,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Description
                    Text(
                        text = pages[index].description,
                        fontSize = 16.sp,
                        color = Color.White.copy(alpha = 0.9f),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(horizontal = 32.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            // Indicators
            Row(
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(pages.size) { index ->
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 4.dp)
                            .size(if (index == pagerState.currentPage) 24.dp else 8.dp, 8.dp)
                            .clip(CircleShape)
                            .background(
                                if (index == pagerState.currentPage)
                                    Color.White
                                else
                                    Color.White.copy(alpha = 0.4f)
                            )
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Button
            Button(
                onClick = {
                    scope.launch {
                        if (pagerState.currentPage < pages.size - 1) {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        } else {
                            onFinished()
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(28.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = animatedColor1
                )
            ) {
                Text(
                    text = if (pagerState.currentPage == pages.size - 1) "Get Started" else "Next",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp
                )
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GradientOnboardingScreenPreview() {
    GradientOnboardingScreen(onFinished = {})
}
