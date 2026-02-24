package com.rejowan.onboardingjc.onboarding.verticalpager

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rejowan.onboardingjc.R
import kotlinx.coroutines.launch

data class VerticalPage(
    val image: Int,
    val title: String,
    val description: String,
    val backgroundColor: Long
)

private val pages = listOf(
    VerticalPage(
        image = R.drawable.img_into_1,
        title = "Swipe Up to Explore",
        description = "Navigate through pages by swiping up and down",
        backgroundColor = 0xFFE3F2FD
    ),
    VerticalPage(
        image = R.drawable.img_into_2,
        title = "Vertical Navigation",
        description = "A unique way to browse through content naturally",
        backgroundColor = 0xFFF3E5F5
    ),
    VerticalPage(
        image = R.drawable.img_into_3,
        title = "Ready to Start",
        description = "You've mastered vertical scrolling. Let's begin!",
        backgroundColor = 0xFFE8F5E9
    )
)

@Composable
fun VerticalPagerOnboardingScreen(onFinished: () -> Unit) {
    val pagerState = rememberPagerState(initialPage = 0) { pages.size }
    val scope = rememberCoroutineScope()

    Surface(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.fillMaxSize()) {
            // Vertical Pager
            VerticalPager(
                state = pagerState,
                modifier = Modifier.fillMaxSize()
            ) { index ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(androidx.compose.ui.graphics.Color(pages[index].backgroundColor))
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        // Image
                        Image(
                            painter = painterResource(id = pages[index].image),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth(0.7f)
                                .weight(1f),
                            contentScale = ContentScale.Fit
                        )

                        Spacer(modifier = Modifier.height(32.dp))

                        // Title
                        Text(
                            text = pages[index].title,
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onBackground,
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        // Description
                        Text(
                            text = pages[index].description,
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(horizontal = 24.dp)
                        )

                        Spacer(modifier = Modifier.height(80.dp))
                    }
                }
            }

            // Skip button at top
            TextButton(
                onClick = onFinished,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(16.dp)
            ) {
                Text("Skip")
            }

            // Vertical indicator on the right
            Column(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 16.dp),
                verticalArrangement = Arrangement.Center
            ) {
                repeat(pages.size) { index ->
                    Box(
                        modifier = Modifier
                            .padding(vertical = 4.dp)
                            .size(8.dp, if (index == pagerState.currentPage) 24.dp else 8.dp)
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

            // Navigation buttons at bottom
            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Up button
                if (pagerState.currentPage > 0) {
                    FloatingActionButton(
                        onClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(pagerState.currentPage - 1)
                            }
                        },
                        containerColor = MaterialTheme.colorScheme.secondaryContainer,
                        modifier = Modifier.size(48.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowUp,
                            contentDescription = "Previous"
                        )
                    }
                } else {
                    Spacer(modifier = Modifier.size(48.dp))
                }

                // Get Started / Down button
                if (pagerState.currentPage == pages.size - 1) {
                    FloatingActionButton(
                        onClick = onFinished,
                        containerColor = MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier
                            .height(48.dp)
                            .width(140.dp)
                    ) {
                        Text(
                            text = "Get Started",
                            color = MaterialTheme.colorScheme.onPrimary,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                } else {
                    FloatingActionButton(
                        onClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(pagerState.currentPage + 1)
                            }
                        },
                        containerColor = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(48.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowDown,
                            contentDescription = "Next"
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun VerticalPagerOnboardingScreenPreview() {
    VerticalPagerOnboardingScreen(onFinished = {})
}
