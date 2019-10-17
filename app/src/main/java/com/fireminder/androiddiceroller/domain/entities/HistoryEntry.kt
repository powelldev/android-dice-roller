package com.fireminder.androiddiceroller.domain.entities

data class HistoryEntry(
    val id: String,
    val dice: String,
    val result: String,
    val total: Int
)