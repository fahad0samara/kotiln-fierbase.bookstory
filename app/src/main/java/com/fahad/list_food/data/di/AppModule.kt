package com.fahad.list_food.data.di

import android.content.Context
import androidx.room.Room
import com.fahad.list_food.data.local.dao.ItemDao
import com.fahad.list_food.data.local.database.AppDatabase
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
    fun provideItemDao(database: AppDatabase): ItemDao=database.itemDao()
}