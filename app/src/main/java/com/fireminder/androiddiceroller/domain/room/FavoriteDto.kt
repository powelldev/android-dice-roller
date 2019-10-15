package com.fireminder.androiddiceroller.domain.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite")
data class FavoriteDto(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "dice")
    val dice: String,

    @ColumnInfo(name = "recentTotal")
    val recentTotal: Int?,

    @ColumnInfo(name = "categoryId")
    val category: Int?
)
