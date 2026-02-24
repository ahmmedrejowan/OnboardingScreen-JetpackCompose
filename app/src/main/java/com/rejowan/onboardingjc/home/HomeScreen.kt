package com.rejowan.onboardingjc.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rejowan.onboardingjc.R
import com.rejowan.onboardingjc.components.VariationCard
import com.rejowan.onboardingjc.navigation.Routes
import com.rejowan.onboardingjc.settings.SettingsManager

@Composable
fun HomeScreen(
    onVariationClick: (String) -> Unit
) {
    val context = LocalContext.current
    val settingsManager = remember { SettingsManager(context) }
    var resetState by remember { mutableStateOf(false) }

    val variations = remember {
        listOf(
            OnboardingVariation(
                id = "classic",
                name = "Classic Onboarding",
                description = "A simple horizontal pager with dots indicator and back/next buttons",
                previewImage = R.drawable.img_into_1,
                hasTutorial = true,
                tutorialUrl = "https://youtube.com/...",
                route = Routes.ClassicOnboarding.route
            )
        )
    }

    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Header
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Onboarding Showcase",
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Explore different onboarding screen variations",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Divider()

            // Settings toggle
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Reset Onboarding State",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = "Enable to re-run completed onboardings",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Switch(
                    checked = resetState,
                    onCheckedChange = { checked ->
                        resetState = checked
                        if (checked) {
                            settingsManager.resetAllOnboardingStates()
                        }
                    }
                )
            }

            Divider()

            // Variations list
            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(variations) { variation ->
                    VariationCard(
                        variation = variation,
                        isCompleted = settingsManager.isOnboardingCompleted(variation.id),
                        onClick = {
                            onVariationClick(variation.route)
                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(onVariationClick = {})
}
