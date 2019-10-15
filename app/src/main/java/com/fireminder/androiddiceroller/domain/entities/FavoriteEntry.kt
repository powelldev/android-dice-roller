package com.fireminder.androiddiceroller.domain.entities

data class FavoriteEntry(
    val id: String,
    val title: String,
    val dice: String,
    val recentTotal: Int,
    val category: Category
)