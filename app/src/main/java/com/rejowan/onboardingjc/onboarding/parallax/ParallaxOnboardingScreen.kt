package com.rejowan.onboardingjc.onboarding.parallax

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.rejowan.onboardingjc.R
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

data class ParallaxPage(
    val image: Int,
    val title: String,
    val description: String
)

private val pages = listOf(
    ParallaxPage(
        image = R.drawable.img_into_1,
        title = "Parallax Effect",
        description = "Watch the images move at different speeds as you swipe"
    ),
    ParallaxPage(
        image = R.drawable.img_into_2,
        title = "Depth & Motion",
        description = "Multi-layered animations create an immersive experience"
    ),
    ParallaxPage(
        image = R.drawable.img_into_3,
        title = "Beautiful Transitions",
        description = "Smooth and elegant page transitions enhance your journey"
    )
)

@Composable
fun ParallaxOnboardingScreen(onFinished: () -> Unit) {
    val pagerState = rememberPagerState(initialPage = 0) { pages.size }
    val scope = rememberCoroutineScope()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            // Parallax Pager
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxSize()
            ) { pageIndex ->
                val pageOffset = (pagerState.currentPage - pageIndex) + pagerState.currentPageOffsetFraction

                Box(modifier = Modifier.fillMaxSize()) {
                    // Background layer - moves slower (parallax effect)
                    Image(
                        painter = painterResource(id = pages[pageIndex].image),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                            .graphicsLayer {
                                // Parallax effect: background moves at 0.5x speed
                                translationX = size.width * pageOffset * 0.5f
                            }
                            .scale(1.2f),
                        contentScale = ContentScale.Crop
                    )

                    // Overlay gradient
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                MaterialTheme.colorScheme.background.copy(alpha = 0.7f)
                            )
                    )

                    // Foreground image - moves faster
                    Image(
                        painter = painterResource(id = pages[pageIndex].image),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .align(Alignment.Center)
                            .offset {
                                // Foreground moves at 1.5x speed for parallax depth
                                IntOffset(
                                    x = (300.dp.toPx() * pageOffset).toInt(),
                                    y = 0
                                )
                            }
                            .alpha(1f - pageOffset.absoluteValue.coerceIn(0f, 1f) * 0.5f),
                        contentScale = ContentScale.Fit
                    )

                    // Content layer - text moves at normal speed
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Bottom
                    ) {
                        Spacer(modifier = Modifier.weight(1f))

                        // Title with parallax
                        Text(
                            text = pages[pageIndex].title,
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onBackground,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .graphicsLayer {
                                    translationX = size.width * pageOffset * 0.3f
                                    alpha = 1f - pageOffset.absoluteValue.coerceIn(0f, 1f)
                                }
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        // Description with different parallax
                        Text(
                            text = pages[pageIndex].description,
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .padding(horizontal = 24.dp)
                                .graphicsLayer {
                                    translationX = size.width * pageOffset * 0.2f
                                    alpha = 1f - pageOffset.absoluteValue.coerceIn(0f, 1f)
                                }
                        )

                        Spacer(modifier = Modifier.height(120.dp))
                    }
                }
            }

            // Skip button
            TextButton(
                onClick = onFinished,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(16.dp)
            ) {
                Text("Skip")
            }

            // Bottom controls
            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
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
                                        MaterialTheme.colorScheme.primary
                                    else
                                        MaterialTheme.colorScheme.outlineVariant
                                )
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

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
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text(
                        text = if (pagerState.currentPage == pages.size - 1) "Get Started" else "Next",
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ParallaxOnboardingScreenPreview() {
    ParallaxOnboardingScreen(onFinished = {})
}
