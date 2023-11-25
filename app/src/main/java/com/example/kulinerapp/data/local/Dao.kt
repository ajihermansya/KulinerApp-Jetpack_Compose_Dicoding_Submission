package com.example.kulinerapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {
    @Query("SELECT * FROM kuliner")
    fun getAllKuliner(): Flow<List<KulinerEntity>>

    @Query("SELECT * FROM kuliner WHERE isFavorite = 1")
    fun getAllFavoriteKuliner(): Flow<List<KulinerEntity>>

    @Query("SELECT * FROM kuliner WHERE id = :id")
    fun getTourism(id: Int): Flow<KulinerEntity>

    @Query("SELECT * FROM kuliner WHERE name LIKE '%' || :query || '%'")
    fun searchKuliner(query: String): Flow<List<KulinerEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllKuliner(tourismList: List<KulinerEntity>)

    @Query("UPDATE kuliner SET isFavorite = :isFavorite WHERE id = :id")
    suspend fun updateFavoriteKuliner(id: Int, isFavorite: Boolean)
}