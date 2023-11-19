package com.fahad.list_food.di

import android.content.Context
import androidx.room.Room
import com.fahad.auth_firebase.domain.repository.AuthRepository
import com.fahad.list_food.data.local.dao.FavoriteDao
import com.fahad.list_food.data.local.dao.ItemDao
import com.fahad.list_food.data.local.database.AppDatabase
import com.fahad.list_food.data.local.repository.FavoriteRepository
import com.fahad.list_food.data.local.repository.ItemRepository
import com.fahad.list_food.data.repository.AuthRepositoryImpl
import com.fahad.list_food.model.FavoriteViewModel
import com.fahad.list_food.model.FoodViewModel
import com.fahad.list_food.ui.screen.UserDataViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
  @Singleton
  @Provides
  fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
    return Room.databaseBuilder(
      context.applicationContext,
      AppDatabase::class.java,
      "list_food_database"
    ).build()
  }

  @Provides
  fun provideItemDao(database: AppDatabase): ItemDao = database.itemDao()

  @Provides
  fun provideFavoriteDao(database: AppDatabase): FavoriteDao = database.favoriteDao()

  @Provides
  fun provideAuthRepository(): AuthRepository {
    return AuthRepositoryImpl()
  }

  @Provides
  fun provideUserDataViewModel(): UserDataViewModel {
    return UserDataViewModel(
      authRepository = AuthRepositoryImpl()
    )
  }
}



