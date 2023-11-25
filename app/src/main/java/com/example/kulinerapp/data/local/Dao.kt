package com.example.kulinerapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {

    @Query("UPDATE kuliner SET isFavorite = :isFavorite WHERE id = :id")
    suspend fun updateFavoriteKuliner(id: Int, isFavorite: Boolean)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllKuliner(kulinerList: List<KulinerEntity>)

    @Query("SELECT * FROM kuliner WHERE isFavorite = 1")
    fun getAllFavoriteKuliner(): Flow<List<KulinerEntity>>

    @Query("SELECT * FROM kuliner")
    fun getAllKuliner(): Flow<List<KulinerEntity>>

    @Query("SELECT * FROM kuliner WHERE name LIKE '%' || :query || '%'")
    fun searchKuliner(query: String): Flow<List<KulinerEntity>>


    @Query("SELECT * FROM kuliner WHERE id = :id")
    fun getKuliner(id: Int): Flow<KulinerEntity>
}