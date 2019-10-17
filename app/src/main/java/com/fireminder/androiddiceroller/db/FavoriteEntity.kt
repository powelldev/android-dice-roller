package com.fireminder.androiddiceroller.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_table")
class FavoriteEntity(
  @PrimaryKey @ColumnInfo(name = "title") val title: String,
  @ColumnInfo(name = "category") val category: String,
  @ColumnInfo(name = "dice") val dice: String,
  @ColumnInfo(name = "mostRecentTotal") val mostRecentTotal: Int
)
