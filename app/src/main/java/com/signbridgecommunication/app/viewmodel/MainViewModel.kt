package com.signbridgecommunication.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.signbridgecommunication.app.data.datastore.OnboardingDataStore
import com.signbridgecommunication.app.data.model.Category
import com.signbridgecommunication.app.data.model.Phrase
import com.signbridgecommunication.app.data.repository.SignBridgeRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: SignBridgeRepository,
    private val onboardingDataStore: OnboardingDataStore
) : ViewModel() {

    val isOnboardingCompleted = onboardingDataStore.isOnboardingCompleted
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), false)

    val categories = repository.getCategories()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val favoritePhrases = repository.getFavoritePhrases()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val recentPhrases = repository.getRecentPhrases()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val stats = repository.getStats()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyMap())

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    val searchResults = _searchQuery
        .debounce(300)
        .flatMapLatest { query ->
            if (query.isBlank()) flowOf(emptyList())
            else repository.searchPhrases(query)
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun completeOnboarding() {
        viewModelScope.launch {
            onboardingDataStore.saveOnboardingCompleted(true)
        }
    }
}
