package com.example.kulinerapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [KulinerEntity::class], version = 1, exportSchema = false)
abstract class KulinerDatabase : RoomDatabase() {
    abstract fun kulinerDao(): Dao
}