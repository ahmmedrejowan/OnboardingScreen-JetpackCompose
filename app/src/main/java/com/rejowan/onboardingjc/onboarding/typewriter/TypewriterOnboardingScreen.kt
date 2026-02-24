package com.rejowan.onboardingjc.onboarding.typewriter

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
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Keyboard
import androidx.compose.material.icons.filled.TextFields
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

data class TypewriterPage(
    val icon: ImageVector,
    val title: String,
    val description: String
)

private val pages = listOf(
    TypewriterPage(
        icon = Icons.Default.Keyboard,
        title = "Type Effect",
        description = "Watch text appear letter by letter"
    ),
    TypewriterPage(
        icon = Icons.Default.Edit,
        title = "Classic Feel",
        description = "Nostalgic typewriter animation"
    ),
    TypewriterPage(
        icon = Icons.Default.TextFields,
        title = "Ready to Write",
        description = "Start your story now"
    )
)

@Composable
fun TypewriterOnboardingScreen(onFinished: () -> Unit) {
    var currentPage by remember { mutableIntStateOf(0) }
    var displayedTitle by remember { mutableStateOf("") }
    var displayedDescription by remember { mutableStateOf("") }
    var showCursor by remember { mutableStateOf(true) }

    // Typewriter effect for title
    LaunchedEffect(currentPage) {
        displayedTitle = ""
        displayedDescription = ""
        val title = pages[currentPage].title
        val description = pages[currentPage].description

        // Type title
        for (i in title.indices) {
            displayedTitle = title.substring(0, i + 1)
            delay(50)
        }

        delay(200)

        // Type description
        for (i in description.indices) {
            displayedDescription = description.substring(0, i + 1)
            delay(30)
        }
    }

    // Cursor blink effect
    LaunchedEffect(Unit) {
        while (true) {
            delay(500)
            showCursor = !showCursor
        }
    }

    val cursorAlpha by animateFloatAsState(
        targetValue = if (showCursor) 1f else 0f,
        animationSpec = tween(100),
        label = "cursor"
    )

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFF1a1a2e)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            // Skip button
            TextButton(
                onClick = onFinished,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(16.dp)
            ) {
                Text("Skip", color = Color(0xFF00d4ff))
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(60.dp))

                // Icon
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF16213e)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = pages[currentPage].icon,
                        contentDescription = null,
                        modifier = Modifier.size(50.dp),
                        tint = Color(0xFF00d4ff)
                    )
                }

                Spacer(modifier = Modifier.height(60.dp))

                // Typewriter title
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = displayedTitle,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Monospace,
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "|",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Monospace,
                        color = Color(0xFF00d4ff).copy(alpha = cursorAlpha)
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Typewriter description
                Text(
                    text = displayedDescription,
                    fontSize = 16.sp,
                    fontFamily = FontFamily.Monospace,
                    color = Color.White.copy(alpha = 0.7f),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 24.dp)
                )

                Spacer(modifier = Modifier.weight(1f))

                // Terminal-style indicators
                Row(
                    horizontalArrangement = Arrangement.Center
                ) {
                    repeat(pages.size) { index ->
                        Text(
                            text = if (index == currentPage) "[●]" else "[ ]",
                            fontFamily = FontFamily.Monospace,
                            color = if (index == currentPage)
                                Color(0xFF00d4ff)
                            else
                                Color.White.copy(alpha = 0.4f),
                            modifier = Modifier.padding(horizontal = 4.dp)
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
                            Text("< Back", color = Color(0xFF00d4ff), fontFamily = FontFamily.Monospace)
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
                        colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF00d4ff),
                            contentColor = Color(0xFF1a1a2e)
                        )
                    ) {
                        Text(
                            text = if (currentPage == pages.size - 1) "$ start" else "Next >",
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.Bold
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
fun TypewriterOnboardingScreenPreview() {
    TypewriterOnboardingScreen(onFinished = {})
}
