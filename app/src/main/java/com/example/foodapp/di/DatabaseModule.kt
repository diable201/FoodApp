package com.example.foodapp.di

import android.content.Context
import androidx.room.Room
import com.example.foodapp.db.MealDao
import com.example.foodapp.db.MealDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): MealDatabase {
        return Room.databaseBuilder(
            appContext,
            MealDatabase::class.java,
            "RssReader"
        ).build()
    }

    @Provides
    fun provideChannelDao(appDatabase: MealDatabase): MealDao {
        return appDatabase.mealDao()
    }
}