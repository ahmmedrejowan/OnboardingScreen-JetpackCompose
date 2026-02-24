package com.rejowan.onboardingjc.onboarding.swipecards

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.ThumbUp
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
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.absoluteValue
import kotlin.math.roundToInt

data class SwipePage(
    val icon: ImageVector,
    val title: String,
    val description: String,
    val cardColor: Color
)

private val pages = listOf(
    SwipePage(
        icon = Icons.Default.Favorite,
        title = "Swipe Right",
        description = "Swipe cards to navigate through content",
        cardColor = Color(0xFFFF6B6B)
    ),
    SwipePage(
        icon = Icons.Default.Star,
        title = "Interactive",
        description = "Drag cards left or right to proceed",
        cardColor = Color(0xFFFFD93D)
    ),
    SwipePage(
        icon = Icons.Default.ThumbUp,
        title = "You Got It!",
        description = "Ready to swipe into action?",
        cardColor = Color(0xFF6BCB77)
    )
)

@Composable
fun SwipeCardsOnboardingScreen(onFinished: () -> Unit) {
    var currentPage by remember { mutableIntStateOf(0) }
    var offsetX by remember { mutableFloatStateOf(0f) }
    var isDragging by remember { mutableIntStateOf(0) }

    val animatedOffsetX by animateFloatAsState(
        targetValue = if (isDragging == 0) 0f else offsetX,
        animationSpec = tween(300),
        finishedListener = {
            if (isDragging == 0 && offsetX.absoluteValue > 150) {
                if (offsetX > 0 && currentPage > 0) {
                    currentPage--
                } else if (offsetX < 0 && currentPage < pages.size - 1) {
                    currentPage++
                }
            }
            offsetX = 0f
        },
        label = "offsetX"
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
                Spacer(modifier = Modifier.height(48.dp))

                Text(
                    text = "Swipe the card",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Swipeable cards stack
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    // Background cards (stack effect)
                    pages.forEachIndexed { index, page ->
                        if (index > currentPage && index <= currentPage + 2) {
                            val stackOffset = (index - currentPage) * 20
                            val stackScale = 1f - (index - currentPage) * 0.05f

                            Card(
                                modifier = Modifier
                                    .size(300.dp, 400.dp)
                                    .graphicsLayer {
                                        translationY = stackOffset.toFloat()
                                        scaleX = stackScale
                                        scaleY = stackScale
                                    },
                                shape = RoundedCornerShape(24.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = page.cardColor.copy(alpha = 0.5f)
                                )
                            ) {}
                        }
                    }

                    // Current card
                    if (currentPage < pages.size) {
                        val rotation = (if (isDragging != 0) offsetX else animatedOffsetX) / 20f
                        val currentOffset = if (isDragging != 0) offsetX else animatedOffsetX

                        Card(
                            modifier = Modifier
                                .size(300.dp, 400.dp)
                                .offset { IntOffset(currentOffset.roundToInt(), 0) }
                                .rotate(rotation)
                                .pointerInput(currentPage) {
                                    detectHorizontalDragGestures(
                                        onDragStart = { isDragging = 1 },
                                        onDragEnd = {
                                            isDragging = 0
                                            if (offsetX.absoluteValue > 150) {
                                                if (offsetX < 0 && currentPage < pages.size - 1) {
                                                    currentPage++
                                                } else if (offsetX > 0 && currentPage > 0) {
                                                    currentPage--
                                                }
                                            }
                                            offsetX = 0f
                                        },
                                        onDragCancel = {
                                            isDragging = 0
                                            offsetX = 0f
                                        },
                                        onHorizontalDrag = { _, dragAmount ->
                                            offsetX += dragAmount
                                        }
                                    )
                                },
                            shape = RoundedCornerShape(24.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = pages[currentPage].cardColor
                            ),
                            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
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
                                        .background(Color.White.copy(alpha = 0.3f)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = pages[currentPage].icon,
                                        contentDescription = null,
                                        modifier = Modifier.size(40.dp),
                                        tint = Color.White
                                    )
                                }

                                Spacer(modifier = Modifier.height(32.dp))

                                Text(
                                    text = pages[currentPage].title,
                                    style = MaterialTheme.typography.headlineMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White,
                                    textAlign = TextAlign.Center
                                )

                                Spacer(modifier = Modifier.height(16.dp))

                                Text(
                                    text = pages[currentPage].description,
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = Color.White.copy(alpha = 0.9f),
                                    textAlign = TextAlign.Center
                                )
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
                                        pages[currentPage].cardColor
                                    else
                                        MaterialTheme.colorScheme.outlineVariant
                                )
                        )
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                // Navigation button
                Button(
                    onClick = {
                        if (currentPage < pages.size - 1) {
                            currentPage++
                        } else {
                            onFinished()
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text(
                        text = if (currentPage == pages.size - 1) "Get Started" else "Next",
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SwipeCardsOnboardingScreenPreview() {
    SwipeCardsOnboardingScreen(onFinished = {})
}
