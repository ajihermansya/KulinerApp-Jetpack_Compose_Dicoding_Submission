package com.example.kulinerapp.data.repository


import com.example.kulinerapp.data.local.Dao
import com.example.kulinerapp.data.local.KulinerEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(private val tourismDao: Dao) {
    fun getAllKuliners() = tourismDao.getAllKuliner()
    fun getAllFavoriteKuliners() = tourismDao.getAllFavoriteKuliner()
    fun getKuliners(id: Int) = tourismDao.getTourism(id)
    fun searchKuliners(query: String) = tourismDao.searchKuliner(query)
    suspend fun insertAllKuliners(tourism: List<KulinerEntity>) = tourismDao.insertAllKuliner(tourism)
    suspend fun updateFavoriteKuliners(id: Int, isFavorite: Boolean) = tourismDao.updateFavoriteKuliner(id, isFavorite)
}