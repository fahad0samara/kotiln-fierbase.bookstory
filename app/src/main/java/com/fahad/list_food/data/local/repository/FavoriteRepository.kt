package com.fahad.list_food.data.local.repository

import com.fahad.list_food.data.local.dao.FavoriteDao
import com.fahad.list_food.data.local.entities.FavoriteItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoriteRepository @Inject constructor(private val favoritesDao: FavoriteDao) {

    suspend fun insertFavorite(favorite: FavoriteItem) = favoritesDao.insert(favorite)
    suspend fun deleteFavorite(favorite: FavoriteItem)=favoritesDao.delete(favorite)

    suspend fun deleteAllFavorite()=favoritesDao.deleteAllFavoriteItems()

    fun getAllFavorite(): Flow<List<FavoriteItem>> {
        return favoritesDao.getFavoriteItems()
    }

    fun isBookInFavorites(bookTitle: String): Flow<Boolean> {
        return favoritesDao.isBookInFavorites(bookTitle)
    }


}