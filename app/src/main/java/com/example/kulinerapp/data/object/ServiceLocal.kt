package com.example.kulinerapp.data.`object`

import android.app.Application
import androidx.room.Room
import com.example.kulinerapp.data.local.KulinerDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceLocal {
    @Provides
    @Singleton
    fun provideDatabase(application: Application) = Room
        .databaseBuilder(application, KulinerDatabase::class.java, "banyuwangi.db")
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    fun provideDao(database: KulinerDatabase) = database.kulinerDao()
}