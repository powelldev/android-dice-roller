package com.fireminder.androiddiceroller.domain.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history")
data class HistoryDto(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "result")
    val result: String,

    @ColumnInfo(name = "dice")
    val dice: String,

    @ColumnInfo(name = "total")
    val recentTotal: Int?
)