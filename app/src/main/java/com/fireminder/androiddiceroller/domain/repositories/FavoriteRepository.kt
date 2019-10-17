package com.fireminder.androiddiceroller.domain.repositories

import com.fireminder.androiddiceroller.domain.entities.FavoriteEntry

interface FavoriteRepository {
    fun getFavorites(): List<FavoriteEntry>
    fun addFavorite(entry: FavoriteEntry)
}