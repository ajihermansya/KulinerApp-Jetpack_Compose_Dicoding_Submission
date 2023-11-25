package com.example.kulinerapp.home.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kulinerapp.data.local.KulinerEntity
import com.example.kulinerapp.data.repository.Repository
import com.example.kulinerapp.utils.StateInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelDetail @Inject constructor(private val repository: Repository) : ViewModel() {
    private val _kuliner = MutableStateFlow<StateInterface<KulinerEntity>>(StateInterface.Loading)
    val kuliners = _kuliner.asStateFlow()

    fun getKuliner(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getKuliners(id)
                .catch { _kuliner.value = StateInterface.Error(it.message.toString()) }
                .collect { _kuliner.value = StateInterface.Success(it) }
        }
    }

    fun updateFavoriteKuliners(id: Int, isFavorite: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateFavoriteKuliners(id, isFavorite)
        }
    }
}