package com.signbridgecommunication.app.data.repository

import com.signbridgecommunication.app.data.model.Category
import com.signbridgecommunication.app.data.model.Phrase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class SignBridgeRepository {
    // Mock data representing database - in a real app, Member 1 would provide Room/Database access
    private val mockCategories = listOf(
        Category("1", "Basic Communication", 0, 12),
        Category("2", "Emergency", 0, 15),
        Category("3", "Hospital", 0, 18),
        Category("4", "School", 0, 13),
        Category("5", "Workplace", 0, 20),
        Category("6", "Travel", 0, 16),
        Category("7", "Shopping", 0, 22),
        Category("8", "Food", 0, 25),
        Category("9", "Social", 0, 30)
    )

    private val mockPhrases = listOf(
        Phrase("1", "Hello", "Basic Communication", true, System.currentTimeMillis() - 1000, 5),
        Phrase("2", "Thank you", "Basic Communication", false, System.currentTimeMillis() - 5000, 3),
        Phrase("3", "I need help", "Emergency", true, System.currentTimeMillis() - 10000, 10),
        Phrase("4", "Where is the hospital?", "Hospital", false, 0, 0)
    )

    fun getCategories(): Flow<List<Category>> = flowOf(mockCategories)
    
    fun getPhrases(): Flow<List<Phrase>> = flowOf(mockPhrases)

    fun getFavoritePhrases(): Flow<List<Phrase>> = flowOf(mockPhrases.filter { it.isFavorite })

    fun getRecentPhrases(): Flow<List<Phrase>> = flowOf(mockPhrases.filter { it.lastUsedTimestamp > 0 }.sortedByDescending { it.lastUsedTimestamp })

    fun searchPhrases(query: String): Flow<List<Phrase>> = flowOf(
        mockPhrases.filter { it.text.contains(query, ignoreCase = true) }
    )

    fun getStats() = flowOf(
        mapOf(
            "Signs Learned" to mockPhrases.sumOf { it.learnCount },
            "Favorite Phrases" to mockPhrases.count { it.isFavorite },
            "Recently Used" to mockPhrases.count { it.lastUsedTimestamp > 0 },
            "Categories Explored" to mockCategories.size
        )
    )
}
