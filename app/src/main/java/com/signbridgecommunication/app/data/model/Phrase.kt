package com.signbridgecommunication.app.data.model

data class Phrase(
    val id: String,
    val text: String,
    val category: String,
    val isFavorite: Boolean = false,
    val lastUsedTimestamp: Long = 0,
    val learnCount: Int = 0
)

data class Category(
    val id: String,
    val name: String,
    val iconResId: Int,
    val phraseCount: Int
)
