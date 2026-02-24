package com.rejowan.onboardingjc.onboarding.neumorphism

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
import androidx.compose.material.icons.filled.Fingerprint
import androidx.compose.material.icons.filled.TouchApp
import androidx.compose.material.icons.filled.Widgets
import androidx.compose.material3.Icon
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class NeumorphPage(
    val icon: ImageVector,
    val title: String,
    val description: String
)

private val pages = listOf(
    NeumorphPage(
        icon = Icons.Default.TouchApp,
        title = "Soft UI Design",
        description = "Experience tactile, touchable interface elements"
    ),
    NeumorphPage(
        icon = Icons.Default.Fingerprint,
        title = "Pressed Effects",
        description = "Elements that feel like they can be pressed"
    ),
    NeumorphPage(
        icon = Icons.Default.Widgets,
        title = "Modern Aesthetic",
        description = "Ready for this soft, minimal experience?"
    )
)

private val neumorphBackground = Color(0xFFE0E5EC)
private val neumorphDark = Color(0xFFA3B1C6)
private val neumorphLight = Color(0xFFFFFFFF)

@Composable
fun NeumorphismOnboardingScreen(onFinished: () -> Unit) {
    var currentPage by remember { mutableIntStateOf(0) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(neumorphBackground)
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
                color = neumorphDark
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(60.dp))

            // Neumorphic card (convex)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(16.dp)
                    .shadow(
                        elevation = 10.dp,
                        shape = RoundedCornerShape(24.dp),
                        ambientColor = neumorphDark,
                        spotColor = neumorphDark
                    )
                    .shadow(
                        elevation = 10.dp,
                        shape = RoundedCornerShape(24.dp),
                        ambientColor = neumorphLight,
                        spotColor = neumorphLight
                    )
                    .clip(RoundedCornerShape(24.dp))
                    .background(neumorphBackground),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(24.dp)
                ) {
                    // Neumorphic icon container (convex)
                    Box(
                        modifier = Modifier
                            .size(120.dp)
                            .shadow(
                                elevation = 8.dp,
                                shape = CircleShape,
                                ambientColor = neumorphDark,
                                spotColor = neumorphDark
                            )
                            .clip(CircleShape)
                            .background(neumorphBackground),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = pages[currentPage].icon,
                            contentDescription = null,
                            modifier = Modifier.size(56.dp),
                            tint = Color(0xFF6366F1)
                        )
                    }

                    Spacer(modifier = Modifier.height(40.dp))

                    Text(
                        text = pages[currentPage].title,
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF2D3748),
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = pages[currentPage].description,
                        fontSize = 16.sp,
                        color = neumorphDark,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Neumorphic indicators
            Row(
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(pages.size) { index ->
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 6.dp)
                            .size(if (index == currentPage) 16.dp else 12.dp)
                            .shadow(
                                elevation = if (index == currentPage) 6.dp else 3.dp,
                                shape = CircleShape,
                                ambientColor = neumorphDark,
                                spotColor = neumorphDark
                            )
                            .clip(CircleShape)
                            .background(
                                if (index == currentPage)
                                    Color(0xFF6366F1)
                                else
                                    neumorphBackground
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
                // Back button (neumorphic)
                if (currentPage > 0) {
                    NeumorphicButton(
                        text = "Back",
                        onClick = { currentPage-- },
                        modifier = Modifier.weight(1f).padding(end = 8.dp)
                    )
                } else {
                    Spacer(modifier = Modifier.weight(1f))
                }

                // Next button (neumorphic)
                NeumorphicButton(
                    text = if (currentPage == pages.size - 1) "Get Started" else "Next",
                    onClick = {
                        if (currentPage < pages.size - 1) {
                            currentPage++
                        } else {
                            onFinished()
                        }
                    },
                    isPrimary = true,
                    modifier = Modifier.weight(1f).padding(start = 8.dp)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun NeumorphicButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isPrimary: Boolean = false
) {
    Box(
        modifier = modifier
            .height(56.dp)
            .shadow(
                elevation = 6.dp,
                shape = RoundedCornerShape(16.dp),
                ambientColor = neumorphDark,
                spotColor = neumorphDark
            )
            .clip(RoundedCornerShape(16.dp))
            .background(if (isPrimary) Color(0xFF6366F1) else neumorphBackground)
            .padding(horizontal = 24.dp),
        contentAlignment = Alignment.Center
    ) {
        TextButton(onClick = onClick) {
            Text(
                text = text,
                fontWeight = FontWeight.SemiBold,
                color = if (isPrimary) Color.White else Color(0xFF2D3748)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NeumorphismOnboardingScreenPreview() {
    NeumorphismOnboardingScreen(onFinished = {})
}
