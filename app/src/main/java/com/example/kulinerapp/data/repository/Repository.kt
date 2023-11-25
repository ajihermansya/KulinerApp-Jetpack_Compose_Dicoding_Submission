package com.example.kulinerapp.data.repository


import com.example.kulinerapp.data.local.Dao
import com.example.kulinerapp.data.local.KulinerEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(private val kulinerDao: Dao) {
    fun getAllKuliners() = kulinerDao.getAllKuliner()
    fun getAllFavoriteKuliners() = kulinerDao.getAllFavoriteKuliner()
    fun getKuliners(id: Int) = kulinerDao.getKuliner(id)
    fun searchKuliners(query: String) = kulinerDao.searchKuliner(query)
    suspend fun insertAllKuliners(kuliner: List<KulinerEntity>) = kulinerDao.insertAllKuliner(kuliner)
    suspend fun updateFavoriteKuliners(id: Int, isFavorite: Boolean) = kulinerDao.updateFavoriteKuliner(id, isFavorite)
}