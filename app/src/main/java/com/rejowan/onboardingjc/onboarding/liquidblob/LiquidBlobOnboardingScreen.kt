package com.rejowan.onboardingjc.onboarding.liquidblob

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
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
import androidx.compose.material.icons.filled.Opacity
import androidx.compose.material.icons.filled.Water
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.math.cos
import kotlin.math.sin

data class LiquidPage(
    val icon: ImageVector,
    val title: String,
    val description: String
)

private val pages = listOf(
    LiquidPage(
        icon = Icons.Default.WaterDrop,
        title = "Liquid Motion",
        description = "Flowing shapes that move like water"
    ),
    LiquidPage(
        icon = Icons.Default.Water,
        title = "Organic Feel",
        description = "Smooth, natural animations that flow"
    ),
    LiquidPage(
        icon = Icons.Default.Opacity,
        title = "Dive In",
        description = "Ready to make a splash?"
    )
)

@Composable
fun LiquidBlobOnboardingScreen(onFinished: () -> Unit) {
    var currentPage by remember { mutableIntStateOf(0) }

    val infiniteTransition = rememberInfiniteTransition(label = "blob")

    val blobProgress by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 2f * Math.PI.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(4000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "blobProgress"
    )

    val blobProgress2 by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 2f * Math.PI.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(5000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "blobProgress2"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0D1B2A))
    ) {
        // Liquid blob background
        Canvas(modifier = Modifier.fillMaxSize()) {
            val centerX = size.width / 2
            val centerY = size.height * 0.35f
            val baseRadius = size.width * 0.35f

            // Draw blob 1
            val path1 = createBlobPath(
                centerX = centerX - 50f,
                centerY = centerY - 50f,
                radius = baseRadius,
                progress = blobProgress,
                waves = 6,
                amplitude = 30f
            )

            drawPath(
                path = path1,
                brush = Brush.radialGradient(
                    colors = listOf(
                        Color(0xFF3A86FF),
                        Color(0xFF8338EC)
                    ),
                    center = Offset(centerX, centerY)
                ),
                style = Fill,
                alpha = 0.8f
            )

            // Draw blob 2
            val path2 = createBlobPath(
                centerX = centerX + 30f,
                centerY = centerY + 30f,
                radius = baseRadius * 0.8f,
                progress = blobProgress2,
                waves = 5,
                amplitude = 25f
            )

            drawPath(
                path = path2,
                brush = Brush.radialGradient(
                    colors = listOf(
                        Color(0xFFFF006E),
                        Color(0xFFFB5607)
                    ),
                    center = Offset(centerX, centerY)
                ),
                style = Fill,
                alpha = 0.6f
            )
        }

        // Skip button
        TextButton(
            onClick = onFinished,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp)
        ) {
            Text("Skip", color = Color.White.copy(alpha = 0.7f))
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(80.dp))

            // Icon on top of blob
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(Color.White.copy(alpha = 0.15f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = pages[currentPage].icon,
                    contentDescription = null,
                    modifier = Modifier.size(50.dp),
                    tint = Color.White
                )
            }

            Spacer(modifier = Modifier.weight(1f))

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
                color = Color.White.copy(alpha = 0.8f),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 24.dp)
            )

            Spacer(modifier = Modifier.height(48.dp))

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
                                    Color(0xFF3A86FF)
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
                        Text("Back", color = Color.White)
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
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF3A86FF),
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

private fun createBlobPath(
    centerX: Float,
    centerY: Float,
    radius: Float,
    progress: Float,
    waves: Int,
    amplitude: Float
): Path {
    val path = Path()
    val points = 100

    for (i in 0..points) {
        val angle = (i.toFloat() / points) * 2f * Math.PI.toFloat()
        val waveOffset = sin(angle * waves + progress) * amplitude
        val r = radius + waveOffset

        val x = centerX + r * cos(angle)
        val y = centerY + r * sin(angle)

        if (i == 0) {
            path.moveTo(x, y)
        } else {
            path.lineTo(x, y)
        }
    }

    path.close()
    return path
}

@Preview(showBackground = true)
@Composable
fun LiquidBlobOnboardingScreenPreview() {
    LiquidBlobOnboardingScreen(onFinished = {})
}
