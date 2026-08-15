package com.signbridgecommunication.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.RecordVoiceOver
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.signbridgecommunication.app.ui.components.*
import com.signbridgecommunication.app.viewmodel.MainViewModel

@Composable
fun HomeScreen(viewModel: MainViewModel) {
    val categories by viewModel.categories.collectAsState()
    val favoritePhrases by viewModel.favoritePhrases.collectAsState()
    val recentPhrases by viewModel.recentPhrases.collectAsState()
    val stats by viewModel.stats.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()

    Scaffold(
        topBar = {
            SignBridgeTopBar(title = "SignBridge")
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            item {
                Text(
                    text = "Hello, Welcome to SignBridge",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(16.dp),
                    color = MaterialTheme.colorScheme.primary
                )
            }

            item {
                SearchBar(
                    query = searchQuery,
                    onQueryChange = { viewModel.updateSearchQuery(it) }
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    QuickActionCard("Learn Signs", Icons.Default.PlayArrow, MaterialTheme.colorScheme.primary) {}
                    QuickActionCard("TTS", Icons.Default.RecordVoiceOver, MaterialTheme.colorScheme.secondary) {}
                    QuickActionCard("Categories", Icons.AutoMirrored.Filled.List, MaterialTheme.colorScheme.tertiary) {}
                    QuickActionCard("Favorites", Icons.Default.Favorite, Color.Red) {}
                }
                Spacer(modifier = Modifier.height(16.dp))
            }

            item {
                SectionHeader("Dashboard Statistics")
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    stats.forEach { (label, value) ->
                        Card(
                            modifier = Modifier.weight(1f),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                        ) {
                            Column(modifier = Modifier.padding(8.dp)) {
                                Text(text = value.toString(), style = MaterialTheme.typography.titleLarge)
                                Text(text = label, style = MaterialTheme.typography.labelSmall)
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
            }

            item {
                SectionHeader("Categories")
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(categories) { category ->
                        CategoryCard(category = category) {}
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
            }

            if (recentPhrases.isNotEmpty()) {
                item { SectionHeader("Recently Used") }
                items(recentPhrases) { phrase ->
                    Box(modifier = Modifier.padding(horizontal = 16.dp)) {
                        PhraseCard(phrase = phrase) {}
                    }
                }
            }

            if (favoritePhrases.isNotEmpty()) {
                item { SectionHeader("Favorites") }
                items(favoritePhrases) { phrase ->
                    Box(modifier = Modifier.padding(horizontal = 16.dp)) {
                        PhraseCard(phrase = phrase) {}
                    }
                }
            }
        }
    }
}
