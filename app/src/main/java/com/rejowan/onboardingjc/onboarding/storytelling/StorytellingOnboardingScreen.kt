package com.rejowan.onboardingjc.onboarding.storytelling

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
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
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Map
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

data class StoryPage(
    val icon: ImageVector,
    val chapter: String,
    val title: String,
    val story: String
)

private val pages = listOf(
    StoryPage(
        icon = Icons.Default.Book,
        chapter = "Chapter 1",
        title = "The Beginning",
        story = "Once upon a time, there was an app that wanted to tell you a story..."
    ),
    StoryPage(
        icon = Icons.Default.Map,
        chapter = "Chapter 2",
        title = "The Journey",
        story = "Through mountains of features and valleys of design, the adventure continues..."
    ),
    StoryPage(
        icon = Icons.Default.EmojiEvents,
        chapter = "Chapter 3",
        title = "The Triumph",
        story = "And now, dear user, you are ready to write your own chapter..."
    )
)

@Composable
fun StorytellingOnboardingScreen(onFinished: () -> Unit) {
    var currentPage by remember { mutableIntStateOf(0) }
    var showChapter by remember { mutableStateOf(false) }
    var showTitle by remember { mutableStateOf(false) }
    var showStory by remember { mutableStateOf(false) }
    var showIcon by remember { mutableStateOf(false) }

    LaunchedEffect(currentPage) {
        showChapter = false
        showTitle = false
        showStory = false
        showIcon = false

        delay(200)
        showIcon = true
        delay(300)
        showChapter = true
        delay(300)
        showTitle = true
        delay(400)
        showStory = true
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF2C3E50),
                        Color(0xFF1A1A2E)
                    )
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
            Text("Skip", color = Color.White.copy(alpha = 0.6f))
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(60.dp))

            // Animated icon
            AnimatedVisibility(
                visible = showIcon,
                enter = fadeIn() + slideInVertically { -50 }
            ) {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFD4AF37).copy(alpha = 0.2f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = pages[currentPage].icon,
                        contentDescription = null,
                        modifier = Modifier.size(50.dp),
                        tint = Color(0xFFD4AF37)
                    )
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            // Animated chapter
            AnimatedVisibility(
                visible = showChapter,
                enter = fadeIn() + slideInVertically { -30 }
            ) {
                Text(
                    text = pages[currentPage].chapter,
                    fontSize = 14.sp,
                    fontStyle = FontStyle.Italic,
                    color = Color(0xFFD4AF37),
                    letterSpacing = 4.sp
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Animated title
            AnimatedVisibility(
                visible = showTitle,
                enter = fadeIn() + slideInVertically { -30 }
            ) {
                Text(
                    text = pages[currentPage].title,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Animated story text
            AnimatedVisibility(
                visible = showStory,
                enter = fadeIn() + slideInVertically { 30 }
            ) {
                Text(
                    text = "\"${pages[currentPage].story}\"",
                    fontSize = 18.sp,
                    fontStyle = FontStyle.Italic,
                    color = Color.White.copy(alpha = 0.8f),
                    textAlign = TextAlign.Center,
                    lineHeight = 28.sp,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            // Page indicators as book pages
            Row(
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(pages.size) { index ->
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .size(
                                width = if (index == currentPage) 40.dp else 24.dp,
                                height = 4.dp
                            )
                            .clip(RoundedCornerShape(2.dp))
                            .background(
                                if (index == currentPage)
                                    Color(0xFFD4AF37)
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
                    TextButton(onClick = { currentPage-- }) {
                        Text("← Previous", color = Color.White.copy(alpha = 0.7f))
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
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFD4AF37),
                        contentColor = Color(0xFF1A1A2E)
                    )
                ) {
                    Text(
                        text = if (currentPage == pages.size - 1) "The End →" else "Continue →",
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StorytellingOnboardingScreenPreview() {
    StorytellingOnboardingScreen(onFinished = {})
}
